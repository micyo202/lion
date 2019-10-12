package com.lion.auth.controller;

import com.lion.common.constant.ResponseStatus;
import com.lion.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * UserController
 * 用户控制器
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/08
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Api("用户认证")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @ApiOperation(value = "获取用户凭证信息", response = Result.class)
    @GetMapping(value = "/principal")
    public Result principal(Principal principal) {
        //获取用户凭证信息
        return Result.success(principal);
    }

    @ApiOperation(value = "移除用户凭证信息", response = Result.class)
    @DeleteMapping(value = "/revoke")
    public Result revoke(String access_token) {
        if (consumerTokenServices.revokeToken(access_token)) {
            return Result.success();
        } else {
            return Result.status(ResponseStatus.FAILURE);
        }
    }

}
