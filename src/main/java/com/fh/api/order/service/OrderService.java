package com.fh.api.order.service;

import com.fh.api.common.ServerResponse;
import com.fh.api.member.model.Member;

public interface OrderService {
    ServerResponse addOrder(Integer payType, Member member, Integer areaId);
}
