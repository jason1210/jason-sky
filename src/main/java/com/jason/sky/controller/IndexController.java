package com.jason.sky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: jason
 * @Date: 2019-03-23
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }



    @RequestMapping("/user/toLogin")
    public String login() {
        return "/user/login";
    }

    @RequestMapping("/user/toRegister")
    public String register() {
        return "/user/register";
    }

    @GetMapping("toUpload")
    public String upload(Model model) {
        model.addAttribute("fileUrl", "http://127.0.0.1:8082/file/upload");
        return "upload";
    }

}
