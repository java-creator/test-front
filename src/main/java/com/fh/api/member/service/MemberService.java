package com.fh.api.member.service;

import com.fh.api.common.ServerResponse;
import com.fh.api.member.model.Member;

import javax.servlet.http.HttpServletRequest;

public interface MemberService {
    ServerResponse addMember(Member member);

    ServerResponse login(Member member);

    ServerResponse isLogin(HttpServletRequest request);

    ServerResponse outLogin(HttpServletRequest request);
}
