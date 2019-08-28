package com.lion.demo.sample.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lion.common.entity.Result;
import com.lion.common.lock.annotation.Locker;
import com.lion.demo.sample.handler.BlockHandler;
import com.lion.demo.sample.handler.FallbackHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * SampleController
 * TODO
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/16
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Api("示例代码控制器类")
@RestController
@Slf4j
public class SampleController {

    /**
     * 端口号
     */
    @Value("${server.port}")
    private String port;

    /**
     * 灰度版本，从zuul服务发起：http://localhost:8400/demo/sample/gray?version=v1.0
     */
    @Value("${spring.cloud.nacos.discovery.metadata.version}")
    private String version;

    @ApiOperation("灰度测试接口")
    @RequestMapping("/gray")
    public String hi() {
        return "灰度版本：" + version + " 端口：" + port;
    }

    @ApiOperation("sentinel流量控制测试接口")
    @RequestMapping("/sentinel/block")
    @SentinelResource(value = "sentinelBlock", blockHandler = "sentinelBlockHandler", blockHandlerClass = BlockHandler.class)
    public String sentinelBlock() {
        return "This is sentinel control service flow!";
    }

    @ApiOperation("sentinel服务降级熔断测试接口")
    @RequestMapping("/sentinel/fallback")
    @SentinelResource(value = "sentinelFallback", fallback = "sentinelFallback", fallbackClass = FallbackHandler.class)
    public String sentinelFallback() {
        throw new RuntimeException();
        //return "This is sentinel control service fallback!";
    }

    @ApiOperation("权限认证 - 无需token即可访问")
    @ApiParam(name = "id", value = "产品主键")
    @RequestMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @ApiOperation("权限认证 - 需要token即可访问")
    @ApiParam(name = "id", value = "订单主键")
    @RequestMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        return "order id : " + id;
    }

    @ApiOperation("角色控制 - 需拥有admin角色")
    @RequestMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //@PreAuthorize("hasRole('ADMIN')")
    public String getAdmin() {
        return "拥有Admin权限可访问!!!!!!";
    }

    @ApiOperation("角色控制 - 需拥有user角色")
    @RequestMapping("/user")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    @PreAuthorize("hasRole('USER')")
    public String getUser() {
        return "拥有User权限可访问......";
    }


    @ApiOperation("获取用户凭证信息")
    @RequestMapping("/principle")
    public OAuth2Authentication getPrinciple(OAuth2Authentication oAuth2Authentication, Principal principal, Authentication authentication) {
        log.info(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        log.info(oAuth2Authentication.toString());
        log.info("principal.toString() " + principal.toString());
        log.info("principal.getName() " + principal.getName());
        log.info("authentication: " + authentication.getAuthorities().toString());

        return oAuth2Authentication;
    }

    @ApiOperation("分布式锁")
    @Locker
    @RequestMapping("/lock")
    public Result lock() {

        try {
            log.info("执行锁中业务逻辑");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.success("分布式锁执行成功！");
    }

}