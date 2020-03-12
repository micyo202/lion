package com.lion.auth.service.impl;

import com.lion.auth.entity.SysRole;
import com.lion.auth.entity.SysUser;
import com.lion.auth.service.IUpmsService;
import com.lion.common.exception.LionException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private IUpmsService upmsService;

    /**
     * 角色前缀
     */
    private static final String ROLE_PREFIX = "ROLE_";

    @Override
    public UserDetails loadUserByUsername(String username) {

        // 获取用户信息
        SysUser sysUser = upmsService.getUserByUsername(username);

        if (ObjectUtils.isEmpty(sysUser)) {
            throw new LionException("用户'" + username + "'不存在");
        }

        // 获取角色信息
        List<SysRole> sysRoles = upmsService.getRoleByUserId(sysUser.getId());
        if (ObjectUtils.isEmpty(sysRoles)) {
            throw new LionException("用户'" + username + "'没有对应的角色信息，请配置");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet();
        sysRoles.stream().forEach(sysRole -> {
            // 角色必须是 ROLE_ 开头，可以在数据库中设置（这里在程序中设置）
            grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + sysRole.getCode().toUpperCase()));

            // 获取菜单列表
            /*
            List<SysMenu> sysMenus = upmsService.getMenuByRoleId(sysRole.getId());
            if (ObjectUtils.isEmpty(sysMenus)) {
                throw new LionException("用户'" + username + "'所在角色没有菜单信息，请配置");
            } else {
                sysMenus.stream().forEach(sysMenu -> grantedAuthorities.add(new SimpleGrantedAuthority(sysMenu.getCode())));
            }
            */
        });

        return new org.springframework.security.core.userdetails.User(
                sysUser.getUsername(),
                sysUser.getPassword(),
                sysUser.getEnabled(),
                sysUser.getAccountNonExpired(),
                sysUser.getCredentialsNonExpired(),
                sysUser.getAccountNonLocked(),
                grantedAuthorities);
    }

}