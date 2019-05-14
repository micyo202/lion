package com.lion.common.lock.manager;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RedissonManager
 * 分布式锁管理类（初始化redis）
 *
 * @author Yanzheng
 * @date 2019/05/08
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Configuration
public class RedissonManager {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Bean
    public RedissonClient getRedisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port);
        //.setTimeout(3000)
        //.setConnectionPoolSize(10)
        //.setConnectionMinimumIdleSize(8)
        return Redisson.create(config);
    }

}
