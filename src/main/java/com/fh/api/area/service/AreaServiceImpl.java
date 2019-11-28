package com.fh.api.area.service;

import com.fh.api.area.mapper.AreaMapper;
import com.fh.api.area.model.Area;
import com.fh.api.common.ServerResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Resource
    private AreaMapper areaMapper;

    @Override
    public ServerResponse addArea(Area area) {
        if(area.getIsDefault() == 1){
            areaMapper.updateAllIsDefault();
        }
        areaMapper.insert(area);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse queryArea() {
        List<Area> areaList = areaMapper.selectList(null);
        return ServerResponse.success(areaList);
    }

    @Override
    public ServerResponse getAreaById(Integer id) {
        Area area = areaMapper.selectById(id);
        return ServerResponse.success(area);
    }

    @Override
    public ServerResponse deleteArea(Integer id) {
        areaMapper.deleteById(id);
        return ServerResponse.success();
    }
}
