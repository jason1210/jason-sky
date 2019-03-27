package com.jason.sky.controller;

import com.jason.sky.entity.User;
import com.jason.sky.service.UserService;
import com.jason.sky.util.IpUtil;
import com.jason.sky.util.UserUtil;
import com.jason.sky.vo.Result;
import com.jason.sky.vo.UserVO;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.jason.sky.util.UserUtil.getUserInfo;

/**
 * @author: jason
 * @Date: 2019-03-23
 */
@CrossOrigin
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/toLogin")
    public String login() {
        return "/login";
    }

    @RequestMapping("/toRegister")
    public String register() {
        return "/user/register";
    }

    @GetMapping("toUpload")
    public String upload(Model model) {
        model.addAttribute("fileUrl", "http://127.0.0.1:8082/file/upload");
        return "upload";
    }

    @ResponseBody
    @PostMapping("/register")
    public Result register(@RequestBody User user, HttpServletRequest request) {
        boolean flag = userService.existsByName(user.getUsername());
        if (flag) {
            return Result.validateError("用户名已存在！");
        }
        user.setGmtCreate(new Date());
        user.setSourceIp(IpUtil.getIpAddr(request));
        boolean res = userService.insert(user);
        if (res) {
            try {
                UserUtil.authenticateUser(user.getUsername(), user.getPassword());
            } catch (Exception e) {
                log.error("用户认证失败" + e);
            }
            return Result.success("注册成功！");
        }
        return Result.sysError("注册失败！");
    }

    @ResponseBody
    @PostMapping("/login")
    public Result login(String username, String password, String code) {

        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
            .getRequest();
        //验证验证码
        String vcode = request.getSession().getAttribute("vcode").toString();
        if (!code.equalsIgnoreCase(vcode)) {
            return Result.validateError("验证码错误！");
        }
        //添加用户认证信息
        try {
            UserUtil.authenticateUser(username, password);
        } catch (AuthenticationException e) {
            return Result.validateError("用户名或密码错误！");
        }
        UserVO userVO = new UserVO();
        userVO.setUserId(getUserInfo().getId());
        userVO.setUsername(getUserInfo().getUsername());
        return Result.success(userVO);
    }

}
