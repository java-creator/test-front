package com.fh.api.category.controller;

import com.alibaba.fastjson.JSONArray;
import com.fh.api.category.model.Category;
import com.fh.api.category.service.CategoryService;
import com.fh.api.common.Ignore;
import com.fh.api.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("category/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据pid查询该节点下的所有子节点
     * @param pid
     * @return
     */
    @RequestMapping("queryListByPid")
    public ServerResponse queryListByPid(Integer pid){
        List list = categoryService.queryListByPid( pid);
        return ServerResponse.success(list);
    }

    @RequestMapping(value="queryList",produces={"text/html;charset=UTF-8;","application/json;"})
    public String queryList(HttpServletRequest request){
        List list= categoryService.queryList();
        String ss = JSONArray.toJSONString(list);
        String callback = request.getParameter("callback");
        String res= callback+"("+ss+")";
        return res;
    }

    @RequestMapping("queryList")
    @Ignore
    public List<Category> queryList(){
        List<Category> list = categoryService.queryList();
        return list;
    }

}
