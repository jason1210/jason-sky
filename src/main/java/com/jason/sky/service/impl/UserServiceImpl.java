package com.jason.sky.service.impl;

import com.jason.sky.entity.User;
import com.jason.sky.mapper.UserMapper;
import com.jason.sky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: jason
 * @Date: 2019-03-24
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean insert(User user) {
        return userMapper.insert(user) > 0 ? true : false;
    }

    public User queryUserById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
