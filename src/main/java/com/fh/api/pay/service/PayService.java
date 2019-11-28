package com.fh.api.pay.service;

import com.fh.api.common.ServerResponse;
import com.fh.api.member.model.Member;

public interface PayService {
    ServerResponse createNative(Member member);

    ServerResponse queryOrderStatus(Member member);
}
