package com.fh.api.category.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.api.category.mapper.CategoryMapper;
import com.fh.api.category.model.Category;
import com.fh.api.util.RedisUtil;
import com.fh.api.util.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;


    @Override
    public List queryListByPid(Integer pid) {
        return categoryMapper.queryListByPid(pid);
    }

    @Override
    public List queryList() {
        String categoryListStr = RedisUtil.get(SystemConstant.REDIS_KEY_CATEGORY_LIST);
        List<Category> categoryList = null;
        if(StringUtils.isEmpty(categoryListStr)){
            categoryList = categoryMapper.queryList();
            String jsonStr = JSON.toJSON(categoryList).toString();
            RedisUtil.set(SystemConstant.REDIS_KEY_CATEGORY_LIST,jsonStr);
        }else {
            categoryList = JSONObject.parseArray(categoryListStr, Category.class);
        }
        return categoryList;
    }

}
