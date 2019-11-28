package com.fh.api.member.controller;

import com.fh.api.common.ServerResponse;
import com.fh.api.member.model.Member;
import com.fh.api.member.service.MemberService;
import com.fh.api.common.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("member/")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("register")
    @Ignore
    public ServerResponse register(Member member){
        return memberService.addMember(member);
    }

    @RequestMapping("login")
    @Ignore
    public ServerResponse login(Member member){
        return memberService.login(member);
    }

    @RequestMapping("isLogin")
    public ServerResponse isLogin(HttpServletRequest request){
        return memberService.isLogin(request);
    }

    @RequestMapping("outLogin")
    public ServerResponse outLogin(HttpServletRequest request){
        return memberService.outLogin(request);
    }

}
