package com.lion.demo.sample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * SampleApplication
 * TODO
 *
 * @author Yanzheng
 * @date 2019/03/29
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@SpringBootApplication
@EnableDiscoveryClient
//@MapperScan({"com.lion.common.mapper", "com.lion.demo.sample..*.mapper"})
@MapperScan("com.lion.**.mapper")
@ComponentScan("com.lion")
public class SampleDemoApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SampleDemoApplication.class, args);
    }

}