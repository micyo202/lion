package com.lion.demo.provider;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * ProviderDemoApplication
 * 服务提供者demo
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/01/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@SpringCloudApplication
@EnableFeignClients
@EnableKnife4j
//@EnableTransactionManagement
@MapperScan(basePackages = {"com.lion.common.mapper", "com.lion.demo.provider..*.mapper"})
@ComponentScan(basePackages = {"com.lion.common", "com.lion.demo.provider"})
public class ProviderDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderDemoApplication.class, args);
    }
}
