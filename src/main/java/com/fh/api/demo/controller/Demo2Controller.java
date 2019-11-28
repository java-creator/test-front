package com.fh.api.demo.controller;

import com.alibaba.fastjson.JSON;
import com.fh.api.common.ServerResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("demo2/")
@CrossOrigin()
public class Demo2Controller {
    @GetMapping(value = "test1",produces={"text/html;charset=UTF-8;","application/json;"})
    public String test1(String callback){
        ServerResponse serverResponse = ServerResponse.success();
        String jsonString = JSON.toJSONString(serverResponse);
        String restString = callback+"("+jsonString+")";
        return restString;
    }
    @GetMapping("test2")
    public ServerResponse test2(HttpServletResponse response){
        //response.setHeader("Access-Control-Allow-Origin","*");
        return ServerResponse.success();
    }
    @GetMapping("test3")

    public ServerResponse test3(){
        //response.setHeader("Access-Control-Allow-Origin","*");
        return ServerResponse.success();
    }
}
