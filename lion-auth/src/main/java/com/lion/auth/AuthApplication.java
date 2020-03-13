package com.lion.auth;

import com.lion.common.config.ScheduleConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * AuthApplication
 * OAuth2权限认证服务
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/2/5
 * Copyright 2020 Yanzheng. All rights reserved.
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.lion.auth.mapper")
@ComponentScan(basePackages = {"com.lion.common", "com.lion.auth"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {ScheduleConfig.class})})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
