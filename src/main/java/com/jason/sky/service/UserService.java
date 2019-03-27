package com.jason.sky.service;

import com.jason.sky.entity.Role;
import com.jason.sky.entity.User;

import java.util.Map;
import java.util.Set;

/**
 * @author: jason
 * @Date: 2019-03-24
 */
public interface UserService {
    User queryUserById(Long userId);

    boolean insert(User user);

    User findByName(String name);

    Role addRole(Map<String, Object> map);

    boolean existsByName(String username);
}
