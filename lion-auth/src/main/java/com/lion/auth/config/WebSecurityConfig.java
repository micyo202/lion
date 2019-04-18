package com.lion.auth.config;

import com.lion.auth.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;

/**
 * WebSecurityConfiguration
 * security配置
 *
 * @author Yanzheng
 * @date 2019/04/08
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

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

}
