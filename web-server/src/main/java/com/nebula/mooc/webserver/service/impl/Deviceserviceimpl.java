package com.nebula.mooc.webserver.service.impl;
import com.nebula.mooc.webserver.pojo.DeviceLogic;
import com.nebula.mooc.webserver.dao.Devicemappers;
import com.nebula.mooc.webserver.service.Deviceservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("Deviceservice")
public class  Deviceserviceimpl implements Deviceservice{
    @Autowired
    private Devicemappers devicemapper;


    @Override
    public   List<DeviceLogic> selectUserByName() {
        return devicemapper.selectUserByName();
    }

    @Override
    public int deleteById(int id) {
        return devicemapper.deleteById(id);
    }

    @Override
    public int add(DeviceLogic DeviceLogic) {
        return devicemapper.add(DeviceLogic);
    }

}
