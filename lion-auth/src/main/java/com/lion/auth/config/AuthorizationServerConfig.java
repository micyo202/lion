/**
 *   Copyright 2019 Yanzheng (https://github.com/micyo202). All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
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
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * AuthorizationServerConfiguration
 * 认证服务配置类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/04/08
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public TokenStore tokenStore() {
        /**
         * redis 存储有状态方式
         */
        return new RedisTokenStore(redisConnectionFactory);
        /**
         * jwt 无状态方式
         */
        //return new JwtTokenStore(jwtAccessTokenConverter());
    }

//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        /**
//         * RSA 非对称方式
//         *
//         * 生成 SHA256 的 lion-jwt.jks 签名文件，有效期 3650 天
//         * 命令：keytool -genkeypair -alias lion-jwt -validity 3650 -keyalg RSA -dname "CN=jwt,OU=jtw,O=jtw,L=zurich,S=zurich,C=CH" -keypass 123456 -keystore lion-jwt.jks -storepass 123456
//         *
//         *
//         * jwt 公钥获取
//         *
//         * 命令：keytool -list -rfc --keystore lion-jwt.jks | openssl x509 -inform pem -pubkey
//         * 密码：123456
//         * -----BEGIN PUBLIC KEY-----
//         * MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhf6oZLygSrszafyxNgL1
//         * N9JggRIRb+eVpmQqPKR/qNJ55yUfduX2F/bxmDYXCFtcEtI+oZ8qnUgeN1OmSZ3N
//         * Ma/22dEDE7EhEkeTD8eRjEvem2hnKDq/4SJ8erl9RfLMfITm8wgS67qmV28zdCZW
//         * G4K8l9/LE0AajZ34xopj0OpTYpnmbbd589tAnQpXGWjRgIW/MFm562b2JBNY6uMH
//         * AAr3DXY/EgycbxhzxwL6F9+tYc2lMfkDyZJqY2LUcw5/hPYli17d+skJKWeHB3+j
//         * 3XHrHuuItoPk7rvV9enAQcTN4l6/6+62VSSmJ1JR609RKrgh1NtcbAeFWzqOHH9u
//         * LwIDAQAB
//         * -----END PUBLIC KEY-----
//         * 将生成的公钥信息存放在 lion-pubkey.cert 文件中
//         *
//         * 注：若使用jar或docker部署，请在jar包根路径或docker容器内创建other/certificate文件夹，将lion-jwt.jks文件放入
//         */
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new FileSystemResource(System.getProperty("user.dir") + "/other/certificate/lion-jwt.jks"), "123456".toCharArray());
//        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//        jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("lion-jwt"));
//        return jwtAccessTokenConverter;
//    }

    @Bean
    public WebResponseExceptionTranslator<OAuth2Exception> webResponseExceptionTranslator() {
        return new CustomWebResponseExceptionTranslator();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 通过jdbc去查询数据库oauth_client_details表验证clientId信息
        clients.withClientDetails(clientDetails());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        // 配置tokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        /**
         * jwt 无状态方式
         */
        //tokenServices.setTokenEnhancer(jwtAccessTokenConverter());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetails());
        // 设置access_token有效时长12小时，默认12小时
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12);
        // 设置refresh_token有效时长7天，默认30天
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
