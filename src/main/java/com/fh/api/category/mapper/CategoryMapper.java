package com.fh.api.category.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.api.category.model.Category;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {

    List queryListByPid(Integer pid);

    List queryList();

}
