package com.fh.api.pay.controller;

import com.fh.api.common.MemberResolver;
import com.fh.api.common.ServerResponse;
import com.fh.api.member.model.Member;
import com.fh.api.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pay/")
public class PayController {
    @Autowired
    private PayService payService;

    @RequestMapping("createNative")
    public ServerResponse createNative(@MemberResolver Member member){
        return payService.createNative(member);
    }


    @RequestMapping("queryOrderStatus")
    public ServerResponse queryOrderStatus(@MemberResolver Member member){
        return payService.queryOrderStatus(member);
    }

}
