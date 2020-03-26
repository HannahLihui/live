package logic.service.serviceimpl;
import logic.pojo.Device;
import logic.mappers.Devicemappers;
import logic.service.Deviceservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class  Deviceserviceimpl implements Deviceservice{
    @Autowired
    private Devicemappers devicemapper;


    @Override
    public   List<Device> selectUserByName() {
        return devicemapper.selectUserByName();
    }

    @Override
    public int deleteById(int id) {
        return devicemapper.deleteById(id);
    }

    @Override
    public int add(Device device) {
        return devicemapper.add(device);
    }

}
