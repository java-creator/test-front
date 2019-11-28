package com.fh.api.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.api.product.model.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by gy on 2019/10/14.
 */

public interface ProductMapper extends BaseMapper<Product> {

    Integer updateStuck(@Param("count") Integer count, @Param("productId") Integer productId);

    List<Product> querySales();
}
