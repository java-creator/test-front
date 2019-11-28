package com.fh.api.product.service;

import com.fh.api.product.mapper.ProductMapper;
import com.fh.api.product.model.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gy on 2019/10/14.
 */
@Service
public class ProductServiceImpl  implements  ProductService{
    @Resource
    private ProductMapper productMapper;

    @Override
    public List<Product> queryList() {
        List<Product> productList = productMapper.selectList(null);
        return productList;
    }

    @Override
    public List<Product> querySales() {
        return productMapper.querySales();
    }
}
