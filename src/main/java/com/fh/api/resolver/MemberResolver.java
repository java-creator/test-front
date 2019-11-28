package com.fh.api.resolver;

import com.fh.api.member.model.Member;
import com.fh.api.util.SystemConstant;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class MemberResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if(methodParameter.hasParameterAnnotation(com.fh.api.common.MemberResolver.class)){
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        Member member = (Member) request.getSession().getAttribute(SystemConstant.SESSION_MEMBER);
        return member;
    }
}
