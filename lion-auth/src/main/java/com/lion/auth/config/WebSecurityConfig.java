package com.lion.auth.config;

import com.lion.auth.service.impl.UserDetailsServiceImpl;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * WebSecurityConfiguration
 * Web安全配置类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/08
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * 获取yml配置文件中不需要拦截的URL
     */
    @Value("#{'${pattern.permit.urls:}'}")
    private String[] permitUrls;

    /**
     * 不进行认证，直接放行的URL
     */
    private static final String[] PATTERN_URLS = {
            "/actuator/**",
            "/druid/**",

            "/webjars/**",
            "/resources/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/v2/api-docs"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 内存方式用户信息（仅测试）
        /*
        String finalPassword = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
        auth
                .inMemoryAuthentication()
                .withUser("admin").password(finalPassword).authorities("READ", "WRITE")
                .and()
                .withUser("guest").password(finalPassword).authorities("READ");
                */

        // 数据库方式用户信息
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 合并不许要拦截的URL数组
        String[] excludeUrls = ArrayUtils.addAll(PATTERN_URLS, permitUrls);

        http
                //.cors()
                //.and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(excludeUrls).permitAll()
                .anyRequest().authenticated();
    }
}
