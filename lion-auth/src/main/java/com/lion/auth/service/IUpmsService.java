package com.lion.auth.service;

import com.lion.auth.entity.SysMenu;
import com.lion.auth.entity.SysRole;
import com.lion.auth.entity.SysUser;

import java.util.List;

/**
 * IUpmsService
 * 用户权限管理接口
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/2/14
 * Copyright 2020 Yanzheng. All rights reserved.
 */
public interface IUpmsService {

    /**
     * 根据用户名获取用户对象信息
     */
    SysUser getUserByUsername(String username);

    /**
     * 根据用户ID获取角色列表信息
     */
    List<SysRole> getRoleByUserId(Integer userId);

    /**
     * 根据角色ID获取菜单列表信息
     */
    List<SysMenu> getMenuByRoleId(Integer roleId);

    /**
     * 根据角色Ids获取菜单列表信息
     */
    List<SysMenu> getMenuByRoleIds(List<Integer> roleIds);
}
