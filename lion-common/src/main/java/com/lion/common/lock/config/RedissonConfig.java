package com.lion.common.lock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RedissonConfig
 * 分布式锁配置类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/3/24
 * Copyright 2020 Yanzheng. All rights reserved.
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host:localhost}")
    private String host;

    @Value("${spring.redis.port:6379}")
    private String port;

    @Value("${spring.redis.password:}")
    private String password;

    @Bean
    public RedissonClient getRedisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port).setPassword(password);
        //.setTimeout(3000)
        //.setConnectionPoolSize(10)
        //.setConnectionMinimumIdleSize(8)
        return Redisson.create(config);
    }

}
