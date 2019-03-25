package com.lion.demo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ConsumerDemoApplication
 * 服务消费者demo
 *
 * @author Yanzheng 严正
 * @date 2019/01/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
public class ConsumerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerDemoApplication.class, args);
    }

}
