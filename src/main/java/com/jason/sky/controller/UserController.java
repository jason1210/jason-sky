package com.jason.sky.controller;

import com.jason.sky.entity.User;
import com.jason.sky.service.UserService;
import com.jason.sky.util.IpUtil;
import com.jason.sky.util.UserUtil;
import com.jason.sky.vo.Result;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author: jason
 * @Date: 2019-03-24
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/getUserDetail/{id}")
    public Result getUserDetail(@PathVariable(name = "id") Long id, @RequestParam(name = "status") String status) {
        User user = userService.queryUserById(id);
        return Result.success(user);
    }
}