package com.jason.sky.shiro;

import com.jason.sky.entity.Role;
import com.jason.sky.entity.User;
import com.jason.sky.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 实现AuthorizingRealm接口用户用户认证
 *
 * @author: jason
 * @Date: 2019-03-25
 */
public class UserRealm extends AuthorizingRealm {

    //用于用户查询
    @Autowired
    private UserService userService;

    protected static Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * 提供用户信息，返回权限信息
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("---------------------------->授权认证：");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String userName = (String)principals.getPrimaryPrincipal();
        //String userId = userService.findByName(userName);
        //Set<Role> roleIdSet = userService.findRoleIdByUid(Integer.parseInt(userId));
        Set<String> roleSet = new HashSet<String>();
        Set<Integer> pemissionIdSet = new HashSet<Integer>();
        Set<String> pemissionSet = new HashSet<String>();
        //for (Role roleInfo : roleIdSet) {
        //    int roleId = roleInfo.getId();
            //roleSet.add( userService.findRoleByRoleId( roleId  ) );
            //将拥有角色的所有权限放进Set里面，也就是求Set集合的并集
            //pemissionIdSet.addAll( userService.findPermissionIdByRoleId(  roleId ));
        //}
        //for (int permissionId : pemissionIdSet) {
            //String permission= userService.findPermissionById( permissionId ).getPermission() ;
            //pemissionSet.add(  permission );
        //}
        // 将角色名称组成的Set提供给授权info
        authorizationInfo.setRoles(roleSet);
        // 将权限名称组成的Set提供给info
        authorizationInfo.setStringPermissions(pemissionSet);

        return authorizationInfo;
    }

    /**
     * 提供帐户信息，返回认证信息
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
        throws AuthenticationException {
        logger.info("---------------------------->登陆验证:");
        String userName = (String)authenticationToken.getPrincipal();
        User user = userService.findByName(userName);
        if (user == null) {
            //用户不存在就抛出异常
            throw new UnknownAccountException();
        }
        if ("LOCKED".equals(user.getStatus())) {
            //用户被锁定就抛异常
            throw new LockedAccountException();
        }
        //密码可以通过SimpleHash加密，然后保存进数据库。
        //此处是获取数据库内的账号、密码、盐值，保存到登陆信息info中
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
            user.getPassword(),
            getName());                   //realm name

        return authenticationInfo;
    }
}
