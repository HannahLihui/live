/*
 * @author Zhanghh
 * @date 2019/5/13
 */
package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.webserver.service.FileService;
import com.nebula.mooc.webserver.service.UserService;
import com.nebula.mooc.webserver.util.CacheUtil;
import com.nebula.mooc.webserver.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录后修改个人信息的控制器
 */
@RestController
@RequestMapping("/api/user/")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @PostMapping(value = "updateUser")
    public Return updateUser(HttpServletRequest request, HttpServletResponse response,
                             UserInfo userInfo, @RequestParam(required = false) MultipartFile file) {
        UserInfo oldUserInfo = CacheUtil.getUserInfo(request);
        // 将Id设置到新的userInfo
        userInfo.setId(oldUserInfo.getId());
        if (file == null || file.isEmpty())
            userInfo.setHeadUrl(oldUserInfo.getHeadUrl());
            // 上传头像
        else {
            String fileExtName;
            if ((fileExtName = FileUtil.isImg(file)) == null)
                return new Return(Constant.CLIENT_FILE_ERROR, "图片格式错误！");
            String fileName = fileService.uploadHead(file, fileExtName);
            if (fileName == null)
                return new Return(Constant.CLIENT_FILE_ERROR, "图片上传失败！");
            userInfo.setHeadUrl(fileName);
        }
        // 上传个人信息
        String newToken = userService.updateUser(userInfo);
        if (newToken == null)
            return new Return(Constant.CLIENT_ERROR_CODE, "修改失败，请重试！");
        userInfo.setEmail(oldUserInfo.getEmail());
        CacheUtil.set(request.getSession(), response, newToken, userInfo);
        return new Return<>(userInfo);
    }

    @PostMapping(value = "getUserInfo")
    public Return getUserInfo(HttpServletRequest request) {
        UserInfo userInfo = CacheUtil.getUserInfo(request);
        return new Return<>(userInfo);
    }

}
