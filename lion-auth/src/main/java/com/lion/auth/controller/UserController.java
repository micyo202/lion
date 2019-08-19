package com.lion.auth.controller;

import com.lion.common.entity.Result;
import com.lion.common.entity.Status;
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
 * TODO
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

    @ApiOperation(value = "获取用户凭证信息", response = Principal.class)
    @GetMapping(value = "/principal")
    public Principal principal(Principal principal) {
        //获取用户凭证信息
        return principal;
    }

    @ApiOperation(value = "移除用户凭证信息", response = Result.class)
    @DeleteMapping(value = "/revoke")
    public Result revoke(String access_token) {
        Result result = new Result();
        if (consumerTokenServices.revokeToken(access_token)) {
            result.setStatus(Status.SUCCESS);
        } else {
            result.setStatus(Status.FAILURE);
        }
        return result;
    }

}
