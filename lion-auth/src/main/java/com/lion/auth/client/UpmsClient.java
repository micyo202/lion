package com.lion.auth.client;

import com.lion.auth.client.fallback.UpmsClientFallback;
import com.lion.common.entity.Menu;
import com.lion.common.entity.Result;
import com.lion.common.entity.Role;
import com.lion.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * UpmsClient
 * Feign服务调用类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/10
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@FeignClient(name = "lion-upms", fallback = UpmsClientFallback.class)
public interface UpmsClient {

    @PostMapping("/getUserByUsername/{username}")
    Result<User> getUserByUsernameFromUpms(@PathVariable("username") String username);

    @PostMapping("/getRoleByUserId/{userId}")
    Result<List<Role>> getRoleByUserIdFromUpms(@PathVariable("userId") Integer userId);

    @PostMapping("/getMenuByRoleId/{roleId}")
    Result<List<Menu>> getMenuByRoleIdFromUpms(@PathVariable("roleId") Integer roleId);

}
