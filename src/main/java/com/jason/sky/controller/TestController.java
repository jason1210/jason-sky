package com.jason.sky.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: jason
 * @Date: 2019-03-23
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/getHost")
    public String getHost() {
        return "http://www.jason2ml.top";
    }
}
