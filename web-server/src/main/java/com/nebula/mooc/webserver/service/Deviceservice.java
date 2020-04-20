package com.nebula.mooc.webserver.service;
import org.apache.ibatis.annotations.Param;
import com.nebula.mooc.webserver.pojo.DeviceLogic;
import java.util.List;
public interface Deviceservice {
    //查询全部
    public List<DeviceLogic> selectUserByName();
    //删除
    int deleteById(@Param("id") int id);
    //添加
    int add(DeviceLogic device);
}
