package com.lion.demo.sample.config;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * ResourceServerConfig
 * TODO
 *
 * @author Yanzheng
 * @date 2019/04/16
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    // 获取yml配置文件中不需要拦截的URL
    @Value("#{'${pattern.permit.urls}'}")
    String[] permitUrls;

    // 不进行认证，直接放行的URL
    private static final String[] PATTERN_URLS = {
            "/actuator/**",
            "/druid/**",

            "/webjars/**",
            "/resources/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/v2/api-docs"
    };

    @Override
    public void configure(HttpSecurity http) throws Exception {

        // 合并不许要拦截的URL数组
        String[] excludeUrls = (String[]) ArrayUtils.addAll(PATTERN_URLS, permitUrls);

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(excludeUrls).permitAll()
                .anyRequest().authenticated();
    }

}
