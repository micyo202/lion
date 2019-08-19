package com.lion.upms.controller;

import com.lion.common.entity.Result;
import com.lion.upms.entity.SysMenu;
import com.lion.upms.entity.SysRole;
import com.lion.upms.entity.SysUser;
import com.lion.upms.repository.MenuRepository;
import com.lion.upms.repository.RoleRepository;
import com.lion.upms.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UpmsController
 * TODO
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/11
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Api("用户权限管理")
@RestController
public class UpmsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MenuRepository menuRepository;

    @ApiOperation("根据用户名获取用户对象信息")
    @ApiParam(name = "username", value = "用户名", required = true)
    @PostMapping("/getUserByUsername/{username}")
    public Result getUserByUsername(@PathVariable("username") String username) {

        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);

        SysUser user = userRepository.findOne(Example.of(sysUser)).orElse(null);

        if (user == null) {
            return Result.failure(100, "用户不存在");
        }

        return Result.success(user);
    }

    @ApiOperation("根据用户ID获取角色列表信息")
    @ApiParam(name = "userId", value = "用户ID", required = true)
    @PostMapping("/getRoleByUserId/{userId}")
    public Result getRoleByUserId(@PathVariable("userId") Integer userId) {
        List<SysRole> roleList = roleRepository.getRoleByUserId(userId);
        return Result.success(roleList);
    }

    @ApiOperation("根据角色ID获取菜单列表信息")
    @ApiParam(name = "roleId", value = "角色ID", required = true)
    @PostMapping("/getMenuByRoleId/{roleId}")
    public Result getMenuByRoleId(@PathVariable("roleId") Integer roleId) {
        List<SysMenu> menuList = menuRepository.getMenuByRoleId(roleId);
        return Result.success(menuList);
    }

}
