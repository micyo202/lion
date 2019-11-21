package com.lion.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

/**
 * Swagger2Config
 * Swagger2 API 文档配置
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/09/26
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Value("${security.oauth2.client.access-token-uri:http://localhost:8888/oauth/token}")
    private String authServer;

    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 访问地址：http://ip:port/swagger-ui.html
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //设置包路径
                .apis(RequestHandlerSelectors.basePackage("com." + applicationName.replace("-", ".")))
                .paths(PathSelectors.any())
                //.paths(PathSelectors.regex("/user.*"))
                .build()
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(securityScheme()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("标题：" + applicationName + " 使用 Swagger2 构建 API 接口文档")
                //描述
                .description("描述：用于 " + applicationName + " 接口查看")
                .termsOfServiceUrl("https://github.com/micyo202")
                //创建人
                .contact(new Contact("Yanzheng", "https://github.com/micyo202", "micyo202@163.com"))
                .version("版本号：1.6.0")
                .build();
    }

    /**
     * 这个类决定了你使用哪种认证方式，我这里使用密码模式
     * 其他方式自己摸索一下，完全莫问题啊
     */
    private SecurityScheme securityScheme() {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(authServer);

        return new OAuthBuilder()
                .name("OAuth2")
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    /**
     * 这里设置 swagger2 认证的安全上下文
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("OAuth2", scopes())))
                .forPaths(PathSelectors.any())
                .build();
    }

    /**
     * 这里是写允许认证的scope
     */
    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("all", "All scope is trusted")
        };
    }

}