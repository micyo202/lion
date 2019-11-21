package com.lion.demo.provider.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lion.common.base.controller.BaseController;
import com.lion.common.entity.Result;
import com.lion.common.lock.annotation.Locker;
import com.lion.demo.provider.handler.BlockHandler;
import com.lion.demo.provider.handler.FallbackHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.security.Principal;
import java.util.*;

/**
 * ProviderDemoController
 * 服务提供者示例代码
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/09/25
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Api("服务提供者示例代码")
@RestController
@Slf4j
public class ProviderDemoController extends BaseController {

    @ApiOperation("基本示例接口，返回Hi文本内容")
    @ApiParam(name = "name", value = "名称", defaultValue = "lion")
    @GetMapping("/hi")
    public Result hi(@RequestParam(defaultValue = "lion") String name) {
        log.info("hi 服务提供者 Provider");
        return Result.success("Hi: '" + name + "', i am from port: " + port);
    }

    @ApiOperation("灰度接口")
    @GetMapping("/gray")
    public Result gray() {
        return Result.success("版本：" + version + " 端口：" + port);
    }

    @ApiOperation("sentinel流量控制接口")
    @GetMapping("/sentinel/block")
    @SentinelResource(value = "sentinelBlock", blockHandler = "sentinelBlockHandler", blockHandlerClass = BlockHandler.class)
    public Result sentinelBlock() {
        return Result.success("This is sentinel control service flow");
    }

    @ApiOperation("sentinel服务熔断、降级接口")
    @GetMapping("/sentinel/fallback")
    @SentinelResource(value = "sentinelFallback", fallback = "sentinelFallback", fallbackClass = FallbackHandler.class)
    public Result sentinelFallback() {
        throw new RuntimeException();
        //return Result.failure(500, "This is sentinel control service fallback");
    }

    @ApiOperation("分布式锁")
    @Locker
    @RequestMapping(value = "/lock", method = {RequestMethod.GET, RequestMethod.POST})
    public Result lock() {
        try {
            log.info("执行锁中业务逻辑");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.success("分布式锁方法执行成功");
    }

    @GetMapping("/rabbit/{flag}")
    public Result send(@PathVariable String flag) {
        if ("map".equals(flag)) {
            Map<String, Object> map = new HashMap<>(3, 1);
            map.put("id", UUID.randomUUID().toString());
            map.put("age", 29);
            map.put("sex", true);
            messageSender.send(map);
        } else if ("list".equals(flag)) {
            List<? extends Serializable> list = Arrays.asList(89757, 19L, 6.66f, "Lion狮子", true);
            messageSender.send(list);
        } else {
            messageSender.send("Hello, Lion / 你好，狮子");
        }
        return Result.success();
    }

    @ApiOperation("权限认证 - 无需Token访问")
    @ApiParam(name = "id", value = "产品主键（无需Token）")
    @RequestMapping(value = "/product/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public Result getProduct(@PathVariable String id) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Result.success("无需Token访问，Product id : " + id);
    }

    @ApiOperation("权限认证 - 需要 Token 访问")
    @ApiParam(name = "id", value = "订单主键（需要Token）")
    @RequestMapping(value = "/order/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public Result getOrder(@PathVariable String id) {
        return Result.success("需要Token访问，Order id : " + id);
    }

    @ApiOperation("角色控制 - 需要拥有admin角色")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //@PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.POST})
    public Result getAdmin() {
        return Result.success("当前用户，拥有Admin权限可访问");
    }

    @ApiOperation("角色控制 - 需拥有user角色")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/user", method = {RequestMethod.GET, RequestMethod.POST})
    public Result getUser() {
        return Result.success("当前用户，拥有User权限可访问......");
    }

    @ApiOperation("获取用户凭证信息")
    @RequestMapping(value = "/principle", method = {RequestMethod.GET, RequestMethod.POST})
    public Result getPrinciple(OAuth2Authentication oAuth2Authentication, Principal principal, Authentication authentication) {
        log.info(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        log.info(oAuth2Authentication.toString());
        log.info("principal.toString() " + principal.toString());
        log.info("principal.getName() " + principal.getName());
        log.info("authentication: " + authentication.getAuthorities().toString());
        return Result.success(oAuth2Authentication);
    }

}