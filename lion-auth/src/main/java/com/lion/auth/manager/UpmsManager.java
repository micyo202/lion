/**
 *   Copyright 2019 Yanzheng (https://github.com/micyo202). All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.lion.auth.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lion.auth.entity.SysMenu;
import com.lion.auth.entity.SysRole;
import com.lion.auth.entity.SysUser;
import com.lion.auth.mapper.SysMenuMapper;
import com.lion.auth.mapper.SysRoleMapper;
import com.lion.auth.mapper.SysUserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UpmsManager
 * 用户角色资源管理
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/5/9
 */
@Api("用户权限管理")
@Slf4j
@Repository
public class UpmsManager {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    private static final String CACHE_KEY = "CACHE_UPMS";

    @ApiOperation("根据用户名获取用户对象信息")
    @ApiParam(name = "username", value = "用户名", required = true)
    @Cacheable(cacheNames = CACHE_KEY + "_USER")
    public SysUser getUserByUsername(String username) {

        // redis 自定义代码方式缓存使用
        /*
        String redisKey = "upms_user_" + username;
        boolean hasKey = RedisUtil.hasKey(redisKey);
        if (hasKey) {
            SysUser user = RedisUtil.getValue(redisKey, SysUser.class);
            RedisUtil.expire(redisKey, 300);    // 设置缓存及有效时间
            RedisUtil.delete(redisKey); // 删除缓存
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
    public List<SysRole> getRoleByUserId(Long userId) {
        return sysRoleMapper.getRoleByUserId(userId);
    }

    @ApiOperation("根据角色ID获取菜单列表信息")
    @ApiParam(name = "roleId", value = "角色ID", required = true)
    @Cacheable(cacheNames = CACHE_KEY + "_MENU")
    public List<SysMenu> getMenuByRoleId(Long roleId) {
        return sysMenuMapper.getMenuByRoleId(roleId);
    }

    @ApiOperation("根据角色Ids获取菜单列表信息")
    @ApiParam(name = "roleIds", value = "角色Ids", required = true)
    @Cacheable(cacheNames = CACHE_KEY + "_MENU")
    public List<SysMenu> getMenuByRoleIds(List<Long> roleIds) {
        return sysMenuMapper.getMenuByRoleIds(roleIds);
    }
}