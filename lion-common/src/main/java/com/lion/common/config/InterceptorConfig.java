package com.lion.common.config;

import com.lion.common.gray.interceptor.GrayFeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * InterceptorConfig
 * 拦截器配置类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/3/24
 * Copyright 2020 Yanzheng. All rights reserved.
 */
@Configuration
public class InterceptorConfig {

    /**
     * feign 请求灰度拦截器
     */
    @Bean
    public RequestInterceptor grayFeignRequestInterceptor() {
        return new GrayFeignRequestInterceptor();
    }

}
