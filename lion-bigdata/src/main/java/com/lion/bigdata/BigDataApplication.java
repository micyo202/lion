package com.lion.bigdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * BigDataApplication
 * 大数据模块
 *
 * @author Yanzheng
 * @date 2019/01/06
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@SpringBootApplication
@EnableEurekaClient
public class BigDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigDataApplication.class, args);
    }

}