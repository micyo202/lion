package com.lion.upms.controller;

import com.lion.common.entity.Result;
import com.lion.upms.entity.SysMenu;
import com.lion.upms.entity.SysRole;
import com.lion.upms.entity.SysUser;
import com.lion.upms.repository.MenuRepository;
import com.lion.upms.repository.RoleRepository;
import com.lion.upms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UpmsController
 * TODO
 *
 * @author Yanzheng
 * @date 2019/04/11
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@RestController
public class UpmsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MenuRepository menuRepository;

    @RequestMapping("/getUserByUsername/{username}")
    public Result getUserByUsername(@PathVariable("username") String username) {

        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);

        SysUser user = userRepository.findOne(Example.of(sysUser)).orElse(null);

        if (user == null) {
            return Result.failure(100, "用户不存在");
        }

        return Result.success(user);
    }

    @RequestMapping("/getRoleByUserId/{userId}")
    public Result getRoleByUserId(@PathVariable("userId") Integer userId) {
        List<SysRole> roleList = roleRepository.getRoleByUserId(userId);
        return Result.success(roleList);
    }

    @RequestMapping("/getMenuByRoleId/{roleId}")
    public Result getMenuByRoleId(@PathVariable("roleId") Integer roleId) {
        List<SysMenu> menuList = menuRepository.getMenuByRoleId(roleId);
        return Result.success(menuList);
    }

}
