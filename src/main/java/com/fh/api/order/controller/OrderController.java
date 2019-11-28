package com.fh.api.order.controller;

import com.fh.api.common.MemberResolver;
import com.fh.api.common.ServerResponse;
import com.fh.api.member.model.Member;
import com.fh.api.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("addOrder")
    public ServerResponse addOrder(Integer payType, @MemberResolver Member member,Integer areaId){
        return orderService.addOrder(payType,member,areaId);
    }
}
