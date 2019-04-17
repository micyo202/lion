package com.lion.auth.client.fallback;

import com.lion.auth.client.UpmsClient;
import com.lion.common.entity.Result;
import org.springframework.stereotype.Service;

/**
 * UpmsClientFallback
 * TODO
 *
 * @author Yanzheng
 * @date 2019/04/10
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Service
public class UpmsClientFallback implements UpmsClient {

    @Override
    public Result getUserByUsername(String username) {
        return Result.failure(100, "调用 getUserByUsername 失败...");
    }


    @Override
    public Result getRoleByUserId(Integer userId) {
        return Result.failure(100, "调用 getRoleByUserId 失败...");
    }

    @Override
    public Result getMenuByRoleId(Integer roleId) {
        return Result.failure(100, "调用 getMenuByRoleId 失败...");
    }
}
