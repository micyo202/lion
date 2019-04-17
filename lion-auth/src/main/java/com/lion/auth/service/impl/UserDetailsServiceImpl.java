package com.lion.auth.service.impl;

import com.lion.auth.client.UpmsClient;
import com.lion.common.entity.Menu;
import com.lion.common.entity.Result;
import com.lion.common.entity.Role;
import com.lion.common.entity.User;
import com.lion.common.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * UserDetailsServiceImpl
 * TODO
 *
 * @author Yanzheng
 * @date 2019/04/10
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Service("userDetailsService")
public class
UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UpmsClient upmsClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Result<User> result = upmsClient.getUserByUsername(username);

        if (null == result.getData()) {
            throw new UsernameNotFoundException("用户：" + username + "不存在！");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;

        User user = result.getData();
        Result<List<Role>> roleResult = upmsClient.getRoleByUserId(user.getId());

        if (roleResult.getCode() == Status.SUCCESS.getCode()) { // 判断获取权限列表是否成功
            List<Role> roleList = roleResult.getData();
            for (Role role : roleList) {
                //角色必须是ROLE_开头，可以在数据库中设置
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getValue().toUpperCase());
                grantedAuthorities.add(grantedAuthority);
                //获取权限
                Result<List<Menu>> perResult = upmsClient.getMenuByRoleId(role.getId());
                if (perResult.getCode() == Status.SUCCESS.getCode()) {  // 判断获取菜单列表是否成功
                    List<Menu> menuList = perResult.getData();
                    for (Menu menu : menuList) {
                        GrantedAuthority authority = new SimpleGrantedAuthority(menu.getCode());
                        grantedAuthorities.add(authority);
                    }
                }
            }
        }

        org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return securityUser;
    }

}
