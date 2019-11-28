package com.fh.api.test.controller;

import com.fh.api.common.Ignore;
import com.fh.api.utils.WeChatParams;
import com.fh.api.utils.WeixinPay;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("qe")
public class QrCodeController {
    //微信支付接口
    @RequestMapping("/wxPay")
    @ResponseBody
    @Ignore
    public String wxPay(WeChatParams ps) throws Exception {
        ps.setBody("测试商品3");
        ps.setTotal_fee("1");
        ps.setOut_trade_no("hw5409550792199899");
        ps.setAttach("xiner");
        ps.setMemberid("888");
        String urlCode = WeixinPay.getCodeUrl(ps);
        System.out.println(urlCode);
        return "";

    }
}
