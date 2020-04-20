package com.nebula.mooc.webserver.controller;
import com.nebula.mooc.webserver.pojo.DeviceLogic;
import com.nebula.mooc.webserver.service.Deviceservice;
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
        List<DeviceLogic> DeviceLogic = this.Deviceservice.selectUserByName();
        model.addAttribute("DeviceLogic", DeviceLogic);
        return "DeviceLogic";
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
    public ModelAndView AddUser(DeviceLogic user) {
        Deviceservice.add(user);
        ModelAndView mav = new ModelAndView("redirect:/all");
        return mav;
    }
}
