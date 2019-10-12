package com.lion.auth.config;

import com.lion.auth.exception.CustomWebResponseExceptionTranslator;
import com.lion.auth.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * AuthorizationServerConfiguration
 * 安全认证服务器
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/08
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public RedisTokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public WebResponseExceptionTranslator<OAuth2Exception> webResponseExceptionTranslator() {
        return new CustomWebResponseExceptionTranslator();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        //String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("secret");
        // 内存验证clientId信息
        /*
        clients.inMemory()
                .withClient("lion-gateway-server")
                .secret(finalSecret)
                .scopes("server")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit");
                */

        // 通过jdbc去查询数据库oauth_client_details表验证clientId信息
        clients.withClientDetails(clientDetails());

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        // 配置tokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        // 使用redis方式
        tokenServices.setTokenStore(redisTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetails());
        // 设置access_token有效时长，默认12小时
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12);
        // 设置refresh_token有效时长，默认30天
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);

        endpoints
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager)
                .tokenServices(tokenServices)
                // 自定义认证异常处理类
                .exceptionTranslator(webResponseExceptionTranslator());

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();

    }

}
