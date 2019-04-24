package com.lion.demo.sample.controller;

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
 * @author Yanzheng
 * @date 2019/04/16
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@RestController
@Slf4j
public class SampleController {

    // 端口号
    @Value("${server.port}")
    String port;

    // 灰度版本，从zuul服务发起：http://localhost:8400/demo/sample/gray?version=v1.0
    @Value("${spring.cloud.nacos.discovery.metadata.version}")
    String version;

    // 灰度发布测试
    @RequestMapping("/gray")
    public String hi() {
        return "灰度版本：" + version + " 端口：" + port;
    }

    // 权限认证 - 无需token即可访问
    @RequestMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    // 权限认证 - 需要token即可访问
    @RequestMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        return "order id : " + id;
    }

    @RequestMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //@PreAuthorize("hasRole('ADMIN')")
    public String getAdmin() {
        return "拥有Admin权限可访问!!!!!!";
    }

    @RequestMapping("/user")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    @PreAuthorize("hasRole('USER')")
    public String getUser() {
        return "拥有User权限可访问......";
    }

    // 获取用户凭证信息
    @RequestMapping("/principle")
    public OAuth2Authentication getPrinciple(OAuth2Authentication oAuth2Authentication, Principal principal, Authentication authentication) {
        log.info(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        log.info(oAuth2Authentication.toString());
        log.info("principal.toString() " + principal.toString());
        log.info("principal.getName() " + principal.getName());
        log.info("authentication: " + authentication.getAuthorities().toString());

        return oAuth2Authentication;
    }

}