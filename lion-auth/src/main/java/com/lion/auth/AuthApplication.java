package com.lion.auth;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.lion.common.amqp.AmqpReceiver;
import com.lion.common.amqp.AmqpSender;
import com.lion.common.config.ScheduleConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
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
@EnableFeignClients
@EnableKnife4j
@MapperScan("com.lion.auth.mapper")
@ComponentScan(basePackages = {"com.lion.auth", "com.lion.common"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = {ScheduleConfig.class, AmqpSender.class, AmqpReceiver.class})})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
