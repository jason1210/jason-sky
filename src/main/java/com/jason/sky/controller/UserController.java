package com.jason.sky.controller;

import com.jason.sky.entity.User;
import com.jason.sky.service.UserService;
import com.jason.sky.util.IpUtil;
import com.jason.sky.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author: jason
 * @Date: 2019-03-24
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserDetail/{id}")
    public Result getUserDetail(@PathVariable(name = "id") Long id, @RequestParam(name = "status") String status) {
        User user = userService.queryUserById(id);
        return Result.success(user);
    }

    @PostMapping("/register")
    public String register(@RequestBody User user, HttpServletRequest request) {
        user.setGmtCreate(new Date());
        user.setSourceIp(IpUtil.getIpAddr(request));
        boolean res = userService.insert(user);
        if (res) {
            return "success";
        }
        return "false";
    }
}