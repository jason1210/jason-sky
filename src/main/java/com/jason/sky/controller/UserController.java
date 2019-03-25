package com.jason.sky.controller;

import com.jason.sky.entity.User;
import com.jason.sky.service.UserService;
import com.jason.sky.util.IpUtil;
import com.jason.sky.vo.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author: jason
 * @Date: 2019-03-24
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ResponseBody
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
            return "/index";
        }
        return "/user/login";
    }
    @PostMapping("/login")
    public String login(@RequestBody Map map){
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
            map.get("username").toString(),
            map.get("password").toString());
        //进行验证，这里可以捕获异常，然后返回对应信息
        subject.login(usernamePasswordToken);
        return "/index";
    }

}