package com.nebula.mooc.webserver.service.impl;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.webserver.service.CodeService;
import com.nebula.mooc.webserver.util.CodeUtil;
import com.nebula.mooc.webserver.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;

/**
 * Created by 15722 on 2019/4/13.
 */

@Service("CodeService")
public class CodeServiceImpl implements CodeService {

    private static final Logger logger = LoggerFactory.getLogger(CodeService.class);

    @Override
    public boolean verifyImgCode(String imgCode, HttpSession session) {
        //提取服务器验证码
        String code = (String) session.getAttribute(Constant.IMG_CHECK_CODE);
        if (imgCode == null || code == null) return false;
        return imgCode.toLowerCase().equals(code.toLowerCase());
    }

    @Override
    public boolean verifyMailCode(String mailCode, HttpSession session) {
        //提取服务器验证码
        String code = (String) session.getAttribute(Constant.EMAIL_CHECK_CODE);
        if (mailCode == null || code == null) return false;
        return mailCode.toLowerCase().equals(code.toLowerCase());
    }

    @Override
    public boolean sendImgCode(HttpServletResponse response, HttpSession session) {
        try {
            String code = CodeUtil.createCode();
            session.setAttribute(Constant.IMG_CHECK_CODE, code);
            OutputStream os = response.getOutputStream();
            ImageIO.write(CodeUtil.createImgCode(code), "png", os);
            os.close();
            System.out.println("验证码为：" + code);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean sendMailCode(HttpServletRequest request, HttpSession session) {
        try {
            String code = CodeUtil.createCode();
            session.setAttribute(Constant.EMAIL_CHECK_CODE, code);
            String receiver = request.getParameter("address");
            if (receiver == null) return false;
            String title = "欢迎注册NebulaMooc!";
            String content = "验证码为：" + code + "，请在网页上输入验证码。";
            System.out.println("邮件验证码为：" + code);
            MailUtil.send(receiver, title, content);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public void clearImgCode(HttpSession session) {
        session.setAttribute(Constant.IMG_CHECK_CODE, "");
    }

    public void clearMailCode(HttpSession session) {
        session.setAttribute(Constant.EMAIL_CHECK_CODE, "");
    }

}
