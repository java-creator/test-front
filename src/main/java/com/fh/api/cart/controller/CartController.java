package com.fh.api.cart.controller;

import com.fh.api.cart.service.CartService;
import com.fh.api.common.MemberResolver;
import com.fh.api.common.ServerResponse;
import com.fh.api.member.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping("add")
    public ServerResponse add(@MemberResolver() Member member, Integer productId, Integer count, HttpServletRequest request){
        return cartService.add(productId,count,member);
    }

    @RequestMapping("queryList")
    public ServerResponse queryList(HttpServletRequest request){
        return cartService.queryList(request);
    }

    @RequestMapping("deleteCartPro")
    public ServerResponse deleteCartPro(@MemberResolver() Member member,Integer productId){
        return cartService.deleteCartPro(productId,member);
    }
}
