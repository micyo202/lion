package com.lion.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * AuthApplication
 * TODO
 *
 * @author Yanzheng
 * @date 2019/04/08
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("com.lion.auth.client")
@EnableHystrix
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
