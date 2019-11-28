package com.fh.api.area.controller;

import com.fh.api.area.model.Area;
import com.fh.api.area.service.AreaService;
import com.fh.api.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @RequestMapping("addArea")
    public ServerResponse addArea(Area area){
        return areaService.addArea(area);
    }

    @RequestMapping("queryArea")
    public ServerResponse queryArea(){
        return areaService.queryArea();
    }

    @RequestMapping("getAreaById")
    public ServerResponse getAreaById(Integer id){
        return areaService.getAreaById(id);
    }

    @RequestMapping("deleteArea")
    public ServerResponse deleteArea(Integer id){
        return areaService.deleteArea(id);
    }

}
