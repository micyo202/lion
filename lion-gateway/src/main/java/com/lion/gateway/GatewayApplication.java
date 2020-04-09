package com.lion.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * GatewayApplication
 * 路由网关服务
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/09/06
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@SpringCloudApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.lion.gateway", "com.lion.common.gray"})
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
