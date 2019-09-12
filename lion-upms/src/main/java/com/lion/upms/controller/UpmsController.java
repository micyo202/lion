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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UpmsController
 * 用户权限管理模块
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/11
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Api("用户权限管理")
@RestController
@Slf4j
public class UpmsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MenuRepository menuRepository;

    private static final String CACHE_KEY = "CACHE_UPMS_";

    @ApiOperation("根据用户名获取用户对象信息")
    @ApiParam(name = "username", value = "用户名", required = true)
    @PostMapping("/getUserByUsername/{username}")
    @Cacheable(cacheNames = CACHE_KEY + "USER")
    public Result getUserByUsername(@PathVariable("username") String username) {

        // redis 自定义代码方式缓存使用
        /*
        String redisKey = "upms_user_" + username;
        ValueOperations<String, SysUser> operations = redisTemplate.opsForValue();
        Boolean hasKey = redisTemplate.hasKey(redisKey);
        if (hasKey) {
            SysUser user = operations.get(redisKey);
            operations.set(redisKey, user, 1000 * 5, TimeUnit.MILLISECONDS);    // 设置缓存及有效时间
            redisTemplate.delete(redisKey); // 删除缓存
        }
        */

        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);

        SysUser user = userRepository.findOne(Example.of(sysUser)).orElse(null);

        if (user == null) {
            log.warn("用户[" + username + "]不存在");
            return Result.failure(100, "用户不存在");
        }

        return Result.success(user);
    }

    @ApiOperation("根据用户ID获取角色列表信息")
    @ApiParam(name = "userId", value = "用户ID", required = true)
    @PostMapping("/getRoleByUserId/{userId}")
    @Cacheable(cacheNames = CACHE_KEY + "ROLE")
    public Result getRoleByUserId(@PathVariable("userId") Integer userId) {
        List<SysRole> roleList = roleRepository.getRoleByUserId(userId);
        return Result.success(roleList);
    }

    @ApiOperation("根据角色ID获取菜单列表信息")
    @ApiParam(name = "roleId", value = "角色ID", required = true)
    @PostMapping("/getMenuByRoleId/{roleId}")
    @Cacheable(cacheNames = CACHE_KEY + "MENU")
    public Result getMenuByRoleId(@PathVariable("roleId") Integer roleId) {
        List<SysMenu> menuList = menuRepository.getMenuByRoleId(roleId);
        return Result.success(menuList);
    }

}
