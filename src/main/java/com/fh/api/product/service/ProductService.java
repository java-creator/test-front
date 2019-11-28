package com.fh.api.product.service;


import com.fh.api.product.model.Product;

import java.util.List;

/**
 * Created by gy on 2019/10/14.
 */
public interface ProductService {
    List<Product> queryList();

    List<Product> querySales();
}
