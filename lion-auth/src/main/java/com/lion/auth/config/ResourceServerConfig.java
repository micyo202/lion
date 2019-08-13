package com.lion.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

/**
 * ResourceServerConfig
 * 资源服务配置
 *
 * @author Yanzheng
 * @date 2019/04/11
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

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

        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                //.authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers(PATTERN_URLS).permitAll()
                .anyRequest().authenticated();

    }

}
