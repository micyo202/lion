package com.lion.demo.sample.config;

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

    // 不进行认证，直接放行的URL
    private static final String[] PATTERNS_URL = {
            "/actuator/**",
            "/druid/**",

            "/webjars/**",
            "/resources/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/v2/api-docs",

            "/product/**",
            "/gray/**",
            "/scala/**"
    };

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(PATTERNS_URL).permitAll()
                .anyRequest().authenticated();
    }

}
