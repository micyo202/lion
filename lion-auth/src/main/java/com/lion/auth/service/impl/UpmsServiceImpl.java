package com.lion.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lion.auth.entity.SysMenu;
import com.lion.auth.entity.SysRole;
import com.lion.auth.entity.SysUser;
import com.lion.auth.mapper.SysMenuMapper;
import com.lion.auth.mapper.SysRoleMapper;
import com.lion.auth.mapper.SysUserMapper;
import com.lion.auth.service.IUpmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UpmsServiceImpl
 * 用户权限管理接口实现类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/2/14
 * Copyright 2020 Yanzheng. All rights reserved.
 */
@Api("用户权限管理")
@Slf4j
@Service
public class UpmsServiceImpl implements IUpmsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    //@Autowired
    //private RedisTemplate redisTemplate;

    private static final String CACHE_KEY = "CACHE_UPMS";

    @ApiOperation("根据用户名获取用户对象信息")
    @ApiParam(name = "username", value = "用户名", required = true)
    @Cacheable(cacheNames = CACHE_KEY + "_USER")
    @Override
    public SysUser getUserByUsername(String username) {

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
        sysUser.setValid(true);

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(sysUser);

        return sysUserMapper.selectOne(queryWrapper);
    }

    @ApiOperation("根据用户ID获取角色列表信息")
    @ApiParam(name = "userId", value = "用户ID", required = true)
    @Cacheable(cacheNames = CACHE_KEY + "_ROLE")
    @Override
    public List<SysRole> getRoleByUserId(Integer userId) {
        return sysRoleMapper.getRoleByUserId(userId);
    }

    @ApiOperation("根据角色ID获取菜单列表信息")
    @ApiParam(name = "roleId", value = "角色ID", required = true)
    @Cacheable(cacheNames = CACHE_KEY + "_MENU")
    @Override
    public List<SysMenu> getMenuByRoleId(Integer roleId) {
        return sysMenuMapper.getMenuByRoleId(roleId);
    }

    @ApiOperation("根据角色Ids获取菜单列表信息")
    @ApiParam(name = "roleIds", value = "角色Ids", required = true)
    @Cacheable(cacheNames = CACHE_KEY + "_MENU")
    @Override
    public List<SysMenu> getMenuByRoleIds(List<Integer> roleIds) {
        return sysMenuMapper.getMenuByRoleIds(roleIds);
    }
}
