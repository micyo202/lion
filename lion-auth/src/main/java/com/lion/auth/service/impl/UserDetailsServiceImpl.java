package com.lion.auth.service.impl;

import com.lion.auth.client.UpmsClient;
import com.lion.common.constant.ResponseStatus;
import com.lion.common.entity.Result;
import com.lion.common.entity.Role;
import com.lion.common.entity.User;
import com.lion.common.exception.LionException;
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
 * 用户授权认证实现类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/10
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UpmsClient upmsClient;

    /**
     * 角色前缀
     */
    private static final String ROLE_PREFIX = "ROLE_";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 获取用户信息
        Result<User> userResult = upmsClient.getUserByUsernameFromUpms(username);

        if (ResponseStatus.SUCCESS.code() != userResult.getCode()) {
            throw new LionException(userResult.getMsg());
        }

        User user = userResult.getData();

        // 获取角色信息
        Result<List<Role>> roleResult = upmsClient.getRoleByUserIdFromUpms(user.getId());

        // 判断获取权限列表是否成功
        if (roleResult.getCode() != ResponseStatus.SUCCESS.code()) {
            throw new LionException(roleResult.getMsg());
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet();
        roleResult.getData().stream().forEach(role -> {
            // 角色必须是 ROLE_ 开头，可以在数据库中设置（这里在程序中设置）
            grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getCode().toUpperCase()));

            // 获取菜单列表
            /*
            Result<List<Menu>> menuResult = upmsClient.getMenuByRoleIdFromUpms(role.getId());
            // 判断获取菜单列表是否成功
            if (menuResult.getCode() != ResponseStatus.SUCCESS.code()) {
                throw new LionException(menuResult.getMsg());
            }
            menuResult.getData().stream().forEach(menu -> grantedAuthorities.add(new SimpleGrantedAuthority(menu.getCode())));
            */
        });

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                user.getAccountNonExpired(),
                user.getCredentialsNonExpired(),
                user.getAccountNonLocked(),
                grantedAuthorities);
    }

}