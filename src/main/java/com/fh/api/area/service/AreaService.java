package com.fh.api.area.service;

import com.fh.api.area.model.Area;
import com.fh.api.common.ServerResponse;

public interface AreaService {
    ServerResponse addArea(Area area);

    ServerResponse queryArea();

    ServerResponse getAreaById(Integer id);

    ServerResponse deleteArea(Integer id);

}
