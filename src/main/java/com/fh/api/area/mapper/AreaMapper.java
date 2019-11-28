package com.fh.api.area.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.api.area.model.Area;

public interface AreaMapper extends BaseMapper<Area> {

    void updateAllIsDefault();
}
