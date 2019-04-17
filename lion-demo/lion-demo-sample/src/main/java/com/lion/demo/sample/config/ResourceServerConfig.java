package com.lion.demo.sample.config;

import org.springframework.context.annotation.Configuration;
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
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/actuator/**", "/druid/**", "/product/**").permitAll() // 配置访问控制，除了product其他必须认证后才可以访问
                .anyRequest().authenticated();
    }

}
