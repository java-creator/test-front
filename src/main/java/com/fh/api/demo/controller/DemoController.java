package com.fh.api.demo.controller;

import com.alibaba.fastjson.JSON;
import com.fh.api.common.ServerResponse;
import com.fh.api.demo.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController //代替@responseBody和@Controller
@RequestMapping("demo/")
public class DemoController {

    @PutMapping("add")
    public void add(@RequestBody User user){
        System.out.println(user.getAge()+"============"+user.getName());
     System.out.println("add");
    }
    @PostMapping("add")
    public void add2(String name,Integer age){
        System.out.println(name+"============"+age);
        System.out.println("add");
    }
    @PostMapping("update")
    public void add3(User user){
        System.out.println(user.getAge()+"============"+user.getName());
        System.out.println("add");
    }

    @DeleteMapping("delete/{id}")
    public void delte(@PathVariable("id") Integer id){
        System.out.println("delete:"+id);
    }
    @GetMapping("delete")
    public void delte2(){
        System.out.println("delete2");
    }
    @PostMapping("delete")
    public void delte3(){
        System.out.println("delete3");
    }
    @GetMapping(value = "testAjax",produces={"text/html;charset=UTF-8;","application/json;"})
    public String testAjax(String callback){
        ServerResponse serverResponse = ServerResponse.success();
        String rest = JSON.toJSONString(serverResponse);
        String result = callback+"("+rest+")";
        return  result;
    }
    @GetMapping("testAjax2")
    @CrossOrigin
    public ServerResponse testAjax2(){

        return ServerResponse.success();
    }

}
