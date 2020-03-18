package com.lion.common.config;

import com.lion.common.constant.SecurityConstant;
import com.lion.common.exception.CustomAccessDeniedHandler;
import com.lion.common.exception.CustomAuthenticationEntryPoint;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * ResourceServerConfig
 * 安全认证资源配置
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/09/26
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 获取yml配置文件中不需要拦截的URL
     */
    @Value("#{'${pattern.permit.urls:}'}")
    private String[] permitUrls;

//    @Bean
//    protected JwtAccessTokenConverter accessTokenConverter() {
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
//         * 注：若使用jar或docker部署，请在jar包根路径或docker容器内创建certificate文件夹，将lion-pubkey.cert文件放入
//         */
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        Resource resource = new FileSystemResource(System.getProperty("user.dir") + "/certificate/lion-pubkey.cert");
//        String publicKey;
//        try {
//            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
//        } catch (IOException e) {
//            throw new LionException("获取jwt公钥信息失败");
//        }
//        converter.setVerifierKey(publicKey);
//        return converter;
//    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                /**
                 * redis 存储有状态方式
                 */
                .tokenStore(new RedisTokenStore(redisConnectionFactory))
                /**
                 * jwt 无状态方式
                 */
                //.tokenStore(new JwtTokenStore(accessTokenConverter()));
                //.authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 合并不许要拦截的URL地址
        String[] excludeUrls = ArrayUtils.addAll(SecurityConstant.PATTERN_URLS, permitUrls);

        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(excludeUrls).permitAll()
                .anyRequest().authenticated();
    }
}