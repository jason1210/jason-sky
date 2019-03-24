package com.jason.sky.service;

import com.jason.sky.entity.User;

/**
 * @author: jason
 * @Date: 2019-03-24
 */
public interface UserService {
    User queryUserById(Long userId);

    boolean insert(User user);
}
