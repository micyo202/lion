package com.lion.zuul.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * WebSecurityConfig
 * TODO
 *
 * @author Yanzheng
 * @date 2019/04/14
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Configuration
@EnableWebSecurity
@Order(99)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.authorizeRequests()
                //.antMatchers("/temp/**").permitAll()
                //.anyRequest()
                //.authenticated()
                //.and()
                .csrf().disable();
    }
}
