package com.fh.api.product.controller;

import com.fh.api.common.Ignore;
import com.fh.api.common.ServerResponse;
import com.fh.api.product.model.Product;
import com.fh.api.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/queryList")
    @Ignore
    public ServerResponse queryList(){
        List<Product> productList = productService.queryList();
        return ServerResponse.success(productList);
    }


    //热销查询
    @RequestMapping("/querySales")
    @Ignore
    public ServerResponse querySales(){
        List<Product> productList = productService.querySales();
        return ServerResponse.success(productList);
    }


}
