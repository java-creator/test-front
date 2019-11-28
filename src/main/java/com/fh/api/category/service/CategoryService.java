package com.fh.api.category.service;

import java.util.List;

public interface CategoryService {
    List queryListByPid(Integer pid);

    List queryList();
}
