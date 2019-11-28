package com.fh.api.pay.service;

import com.alibaba.fastjson.JSON;
import com.fh.WXpay.MyConfig;
import com.fh.WXpay.WXPay;
import com.fh.api.common.ServerResponse;
import com.fh.api.member.model.Member;
import com.fh.api.order.mapper.OrderMapper;
import com.fh.api.order.model.Order;
import com.fh.api.payLog.mapper.PayLogMapper;
import com.fh.api.payLog.model.PayLog;
import com.fh.api.util.RedisUtil;
import com.fh.api.util.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class PayServiceImpl implements PayService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private PayLogMapper payLogMapper;



    @Override
    public ServerResponse createNative(Member member) {

        String string = RedisUtil.get(SystemConstant.FAT_KEY+member.getId());
        if (StringUtils.isEmpty(string)){
            return ServerResponse.error();
        }
        PayLog payLog = JSON.parseObject(string, PayLog.class);
        try {
            MyConfig config = new MyConfig();
            WXPay wxpay = new WXPay(config);

            Map<String, String> data = new HashMap<String, String>();
            data.put("body", "飞狐");
            data.put("out_trade_no", payLog.getOutTradeNo());
            data.put("device_info", "");
            data.put("total_fee", "1");
            data.put("spbill_create_ip", "127.0.0.1");
            data.put("notify_url", "http://www.example.com/wxpay/notify");
            data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
            Date date = DateUtils.addMinutes(new Date(), 2);
            SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmss");
            data.put("time_expire",sim.format(date));

            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println(resp);
            //判断return_code是否成功
            if (!resp.get("return_code").equalsIgnoreCase("SUCCESS")){
                return ServerResponse.error("微信支付报错"+resp.get("return_msg"));
            }
            //判断result_code是否成功
            if (!resp.get("return_code").equalsIgnoreCase("SUCCESS")){
                return ServerResponse.error("微信支付报错"+resp.get("err_code_des"));
            }
            Map<String, String> map = new HashMap<>();
            map.put("totalPrice",payLog.getPayMoney()+"");
            map.put("code_url",resp.get("code_url"));
            map.put("tradeNo",payLog.getOutTradeNo());

            return  ServerResponse.success(map);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ServerResponse queryOrderStatus(Member member) {

        String string = RedisUtil.get(SystemConstant.FAT_KEY+member.getId());
        if (StringUtils.isEmpty(string)){
            return ServerResponse.error();
        }
        int count=0;
        PayLog payLog = JSON.parseObject(string, PayLog.class);

        try {
        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", payLog.getOutTradeNo());
        for (;;){
            Map<String, String> resp = wxpay.orderQuery(data);
            System.out.println(resp);
            //判断return_code是否成功
            if (!resp.get("return_code").equalsIgnoreCase("SUCCESS")){
                return ServerResponse.error("微信支付报错"+resp.get("return_msg"));
            }
            //判断result_code是否成功
            if (!resp.get("return_code").equalsIgnoreCase("SUCCESS")){
                return ServerResponse.error("微信支付报错"+resp.get("err_code_des"));
            }
            //判断支付结果是否成功
            if (!resp.get("trade_state").equalsIgnoreCase("SUCCESS")){
                //修改订单状态
                String orderId = payLog.getOrderId();
                Order order = orderMapper.selectById(orderId);
                order.setStatus(SystemConstant.ORDER_STATUS_SUCCESS);
                orderMapper.updateById(order);
                //修改支付日志状态
                payLog.setPayTime(new Date());
                payLog.setStatus(SystemConstant.FAT_STATUS_SUCCESS);
                //删除redis的支付日志
                RedisUtil.del(SystemConstant.FAT_KEY+member.getId());
                return ServerResponse.success();
            }
            Thread.sleep(3000);
            count++;
            if (count>40){
                //返回支付超时
                return ServerResponse.success(SystemConstant.FAT_TIME_OUT);
            }
        }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
