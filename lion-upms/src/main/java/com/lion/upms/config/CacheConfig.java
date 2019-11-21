package com.lion.upms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

/**
 * CacheConfig
 * 缓存配置类
 *
 * @author Yanzheng
 * @date 2019/11/21
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * 配置 redis 缓存失效时间
     */
    @Bean
    public CacheManager cacheManager(@Autowired RedisConnectionFactory connectionFactory) {
        return RedisCacheManager
                .builder(connectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)))
                .transactionAware()
                .build();
    }

}
