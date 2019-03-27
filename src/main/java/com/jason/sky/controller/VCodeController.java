package com.jason.sky.controller;

import com.jason.sky.service.VCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 图片验证码控制器 Created by gameloft9 on 2017/12/6.
 */
@Slf4j
@Controller
public class VCodeController {

    @Autowired
    VCodeService vCodeServiceImpl;

    static {
        //防止二维码显示不了
        System.setProperty("java.awt.headless", "true");
    }

    /**
     * 获取验证码
     *
     * @param response
     */
    @RequestMapping(value = "/getVCode", method = RequestMethod.GET)
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) throws Exception {
        vCodeServiceImpl.outPutVCode(request, response);
    }
}
