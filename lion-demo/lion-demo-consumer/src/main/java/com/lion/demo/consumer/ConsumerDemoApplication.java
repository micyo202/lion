package com.lion.demo.consumer;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * ConsumerDemoApplication
 * 服务消费者demo
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/01/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableKnife4j
//@EnableTransactionManagement
//@MapperScan("com.lion.**.mapper")
@MapperScan(basePackages = {"com.lion.common.mapper", "com.lion.demo.consumer.**.mapper"})
//@ComponentScan("com.lion")
@ComponentScan(basePackages = {"com.lion.common", "com.lion.demo.consumer"})
public class ConsumerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerDemoApplication.class, args);
    }

}