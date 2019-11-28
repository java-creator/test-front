package com.fh.api.cart.service;

import com.fh.api.common.ServerResponse;
import com.fh.api.member.model.Member;

import javax.servlet.http.HttpServletRequest;

public interface CartService {
    ServerResponse add(Integer productId, Integer count, Member member);

    ServerResponse queryList(HttpServletRequest request);

    ServerResponse deleteCartPro(Integer productId, Member member);
}
