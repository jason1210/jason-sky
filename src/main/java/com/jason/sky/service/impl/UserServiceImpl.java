package com.jason.sky.service.impl;

import com.jason.sky.entity.Permission;
import com.jason.sky.entity.Role;
import com.jason.sky.entity.User;
import com.jason.sky.mapper.RoleMapper;
import com.jason.sky.mapper.UserMapper;
import com.jason.sky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: jason
 * @Date: 2019-03-24
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    public boolean insert(User user) {
        return userMapper.insert(user) > 0 ? true : false;
    }

    public User queryUserById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    //添加角色
    public Role addRole(Map<String, Object> map) {
        User user = userMapper.selectByPrimaryKey(Long.valueOf(map.get("id").toString()));
        Role role = new Role();
        role.setRoleName(map.get("roleName").toString());
        role.setUser(user);
        Permission permission1 = new Permission();
        permission1.setPermission("create");
        permission1.setRole(role);
        Permission permission2 = new Permission();
        permission2.setPermission("update");
        permission2.setRole(role);
        List<Permission> permissions = new ArrayList<Permission>();
        permissions.add(permission1);
        permissions.add(permission2);
        role.setPermissions(permissions);
        roleMapper.insert(role);
        return role;
    }
}
