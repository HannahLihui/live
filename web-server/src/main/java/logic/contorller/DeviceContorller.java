package logic.contorller;
import logic.pojo.Device;
import logic.service.Deviceservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class DeviceContorller {
    @Autowired
    private Deviceservice Deviceservice;

    @GetMapping("all")
    public String all(Model model) {
        List<Device> Devices = this.Deviceservice.selectUserByName();
        model.addAttribute("Devices", Devices);
        return "Devices";
    }




    @GetMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        Deviceservice.deleteById(id);
        ModelAndView mav = new ModelAndView("redirect:/all");
        return mav;
    }

    @RequestMapping("Add1")
    public ModelAndView Add1() {
        return new ModelAndView("add");
    }

    /**
     * @Description: 添加
     * @Author:
     * @CreateDate:
     */
    @RequestMapping("AddUser")
    public ModelAndView AddUser(Device user) {
        Deviceservice.add(user);
        ModelAndView mav = new ModelAndView("redirect:/all");
        return mav;
    }
}
