package com.fh.api.interceptor;

import com.alibaba.fastjson.JSON;
import com.fh.api.common.AjaxException;
import com.fh.api.common.Ignore;
import com.fh.api.common.ServerResponse;
import com.fh.api.member.model.Member;
import com.fh.api.util.JwtUtils;
import com.fh.api.util.SystemConstant;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class LoginInterceptor2 extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler){
        System.err.println("------------登录拦截器---------------");
        //处理跨域问题
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:8081");
        //处理客户端传过来的自定义头信息
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "x-auth,token,content-type");
        //处理客户端发过来put,delete
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "PUT,POST,DELETE,GET");

        //判断是否是预请求
        String methodAction = request.getMethod();
        if(methodAction.equalsIgnoreCase("options")){
            return false;
        }

        //判断是否自定义注解   有则直接放行
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(Ignore.class)){
            return true;
        }

        //获取token
        String token = request.getHeader("x-auth");
        if(StringUtils.isEmpty(token)){
            throw new AjaxException(SystemConstant.TOKEN_IS_NULL);
        }

        //解析token
        ServerResponse serverResponse = JwtUtils.validateJWT(token);
        if(serverResponse.getStatus() != 200){
            //解析失败
            throw new AjaxException(SystemConstant.TOKEN_CHECK_ERROR);
        }

        //获取token里面的信息
        Claims claims = (Claims) serverResponse.getData();
        String memberStr = claims.getSubject();
        Member member = JSON.parseObject(memberStr, Member.class);

        //把member放入session中
        request.getSession().setAttribute(SystemConstant.SESSION_MEMBER,member);

        return true;
    }
}
