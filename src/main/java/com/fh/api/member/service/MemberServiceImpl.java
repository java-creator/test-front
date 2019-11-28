package com.fh.api.member.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.api.common.ResponseEnum;
import com.fh.api.common.ServerResponse;
import com.fh.api.member.mapper.MemberMapper;
import com.fh.api.member.model.Member;
import com.fh.api.util.JwtUtils;
import com.fh.api.util.RedisUtil;
import com.fh.api.util.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberMapper memberMapper;

    @Override
    public ServerResponse addMember(Member member) {
        if(StringUtils.isEmpty(member.getSmsCode())){
            return ServerResponse.error(ResponseEnum.CODE_NULL);
        }else {
            String memberStr = RedisUtil.get(member.getPhone());
            if(!member.getSmsCode().equals(memberStr)){
                return ServerResponse.error(ResponseEnum.CODE_ERROR);
            }
        }

        QueryWrapper<Member> queryWrapper = new QueryWrapper();
        queryWrapper.eq("memberName",member.getMemberName());
        Member member1 = memberMapper.selectOne(queryWrapper);
        if(member1 != null){
            return ServerResponse.error(ResponseEnum.MEMBER_NAME_EXIST);
        }

        QueryWrapper<Member> queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("phone",member.getPhone());
        Member member2 = memberMapper.selectOne(queryWrapper1);
        if(member2 != null){
            return ServerResponse.error(ResponseEnum.PHONE_EXIST);
        }

        memberMapper.insert(member);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse login(Member member) {
        if(StringUtils.isEmpty(member.getMemberName()) || StringUtils.isEmpty(member.getPassWord())){
            return ServerResponse.error(ResponseEnum.MEMBER_NAME_PASSWORD_NULL);
        }

        QueryWrapper<Member> queryWrapper = new QueryWrapper();
        queryWrapper.eq("memberName",member.getMemberName());
        Member member1 = memberMapper.selectOne(queryWrapper);
        if(member1 == null){
            return ServerResponse.error(ResponseEnum.MEMBER_NULL);
        }else {
            if(member.getPassWord().equals(member1.getPassWord())){
                String jsonStr = JSONObject.toJSONString(member1);
                String token = JwtUtils.createJWT(member1.getId().toString(),jsonStr, SystemConstant.JWT_OUT_TIME);
                return ServerResponse.success(token);
            }else {
                return ServerResponse.error(ResponseEnum.PASSWORD_NOT);
            }
        }
    }

    @Override
    public ServerResponse isLogin(HttpServletRequest request) {
        Member member = (Member) request.getSession().getAttribute(SystemConstant.SESSION_MEMBER);
        if(member == null){
            return ServerResponse.error(ResponseEnum.NOT_LOGIN);
        }
        return ServerResponse.success(member);
    }

    @Override
    public ServerResponse outLogin(HttpServletRequest request) {
        request.getSession().invalidate();
        return ServerResponse.success();
    }
}
