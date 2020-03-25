package com.lion.common.config;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.lion.common.gray.interceptor.GrayHttpRequestInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * RestTemplateConfig
 * RestTemplate 配置类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/3/24
 * Copyright 2020 Yanzheng. All rights reserved.
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    @SentinelRestTemplate
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // 将自定义的 ClientHttpRequestInterceptor 添加到 RestTemplate 中，可添加多个
        restTemplate.setInterceptors(Collections.singletonList(new GrayHttpRequestInterceptor()));
        return restTemplate;
    }

}
