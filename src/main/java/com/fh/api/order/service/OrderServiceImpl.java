package com.fh.api.order.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fh.api.cart.model.Cart;
import com.fh.api.cart.model.CartInfo;
import com.fh.api.cart.service.CartServiceImpl;
import com.fh.api.common.ResponseEnum;
import com.fh.api.common.ServerResponse;
import com.fh.api.member.model.Member;
import com.fh.api.order.mapper.OrderDetailMapper;
import com.fh.api.order.mapper.OrderMapper;
import com.fh.api.order.model.Order;
import com.fh.api.order.model.OrderDetail;
import com.fh.api.payLog.mapper.PayLogMapper;
import com.fh.api.payLog.model.PayLog;
import com.fh.api.product.mapper.ProductMapper;
import com.fh.api.product.model.Product;
import com.fh.api.util.BigDecimalUtil;
import com.fh.api.util.IdUtil;
import com.fh.api.util.RedisUtil;
import com.fh.api.util.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private PayLogMapper payLogMapper;

    @Override
    public ServerResponse addOrder(Integer payType, Member member,Integer areaId) {
        //获取购物车中的信息
        String cartStr = RedisUtil.hget(SystemConstant.REDIS_KEY_CART, member.getId().toString());
        if(StringUtils.isEmpty(cartStr)){
            return ServerResponse.error(ResponseEnum.CART_NULL);
        }



        //存放库存不足的商品
        List<CartInfo> shortCartList = new ArrayList<>();

        //生成order的id
        String orderId = IdUtil.createId();

        Cart cart = JSON.parseObject(cartStr, Cart.class);
        //获取购物车中的详情
        List<CartInfo> cartInfoList = cart.getCartInfoList();
        int count=cartInfoList.size();
        Long totalCount = 0l;
        BigDecimal totalPrice = new BigDecimal("0");
        for (CartInfo cartInfo : cartInfoList) {
            //判断库存是否充足
            Product product = productMapper.selectById(cartInfo.getProductId());
            if(product.getStuck() >= cartInfo.getCount()){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setCount(Long.valueOf(cartInfo.getCount()));
                orderDetail.setFilePath(cartInfo.getFilePath());
                orderDetail.setPrice(new BigDecimal(cartInfo.getPrice()));
                orderDetail.setProductName(cartInfo.getProductName());
                orderDetail.setOrderId(orderId);
                orderDetail.setTotalPrice(new BigDecimal(cartInfo.getTotalPrice()));
                orderDetail.setProductId(cartInfo.getProductId());
                //减库存的同时 再次对库存进行判断
                Integer updateCount = productMapper.updateStuck(cartInfo.getCount(),cartInfo.getProductId());
                if(updateCount == 0){
                    //库存不足
                    shortCartList.add(cartInfo);
                }else {
                    //库存足
                    totalCount += orderDetail.getCount();
                    totalPrice = BigDecimalUtil.add(totalPrice,orderDetail.getTotalPrice());
                    orderDetailMapper.insert(orderDetail);
                }
            }else {
                //库存不足
                shortCartList.add(cartInfo);
            }
        }

        //更行购物车


        //生成订单 先判断是否有订单详情 如果订单为空则没必要生成订单
        if(count == shortCartList.size()){
            return ServerResponse.error(ResponseEnum.ORDER_DETAIL_NULL);
        }

        Order order = new Order();
        order.setId(orderId);
        order.setCreateTime(new Date());
        order.setAreaId(areaId);
        order.setPayType(payType);
        order.setStatus(SystemConstant.ORDER_STATUS_WAIT);
        order.setTotalCount(totalCount);
        order.setTotalPrice(totalPrice);
        order.setUserId(member.getId());
        orderMapper.insert(order);

        //生成交易日志
        PayLog payLog = new PayLog();
        payLog.setUsrId(member.getId());
        payLog.setPayMoney(order.getTotalPrice());
        payLog.setPayType(order.getPayType());
        payLog.setCreateDate(new Date());
        payLog.setOutTradeNo(IdWorker.getId()+"");
        payLog.setOrderId(orderId);
        payLog.setStatus(SystemConstant.FAT_STATUS_WAIT);
        payLogMapper.insert(payLog);
        String payLogString = JSON.toJSONString(payLog);
        RedisUtil.set(SystemConstant.FAT_KEY+member.getId(),payLogString);


       //RedisUtil.set(SystemConstant.REDIS_KEY_CART,member.getId().toString());

        return ServerResponse.success(shortCartList);
    }
}
