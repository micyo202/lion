package com.lion.demo.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * ProviderDemoApplication
 * 服务提供者demo
 *
 * @author Yanzheng 严正
 * @date 2019/01/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@SpringBootApplication
@EnableEurekaClient
//@EnableTransactionManagement
@MapperScan("com.lion.demo.provider.mapper")
//@MapperScan("com.lion.demo.provider..*.mapper")
public class ProviderDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderDemoApplication.class, args);
    }
}