package logic.service;
import org.apache.ibatis.annotations.Param;
import logic.pojo.Device;
import java.util.List;
public interface Deviceservice {
    //查询全部
    public List<Device> selectUserByName();
    //删除
    int deleteById(@Param("id") int id);
    //添加
    int add(Device device);
}
