package com.nebula.mooc.webserver.dao;

import com.nebula.mooc.webserver.pojo.DeviceLogic;
import org.apache.ibatis.annotations.*;
import java.util.List;
public interface Devicemappers {
    //查询全部
    //在这里我利用了sql优化，之查出相应的字段，避免出现*号
    @Select("select *  from device_logic")
    List<DeviceLogic> selectUserByName();

    //删除 按id删除
    @Delete("delete from student where id = #{id}")
    int deleteById(@Param("id") int id);

    //添加
    //注意字段的对应
    @Insert({"insert into student(id, name, sex, age, classes) values(#{id}, #{name}, #{sex}, #{age}, #{classes})"})
    int add(DeviceLogic device);
}
