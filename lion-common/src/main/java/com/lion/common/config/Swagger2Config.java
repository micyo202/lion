package com.lion.common.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * Swagger2Config
 * Swagger2 配置
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/09/26
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Value("${spring.cloud.nacos.discovery.metadata.version}")
    private String version;

    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 访问地址：http://ip:port/doc.html
     */
    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName(version)
                .select()
                //设置包路径
                .apis(RequestHandlerSelectors.basePackage("com." + applicationName.replace("-", ".")))
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                //.paths(PathSelectors.regex("/lion.*"))
                .build()
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.<SecurityScheme>newArrayList(apiKey()));
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //标题
                .title(applicationName + " 的 RESTful APIs 文档")
                //描述
                .description("用于 " + applicationName + " 接口查看及调试")
                .termsOfServiceUrl("https://github.com/micyo202/lion")
                //创建人
                .contact(new Contact("Yanzheng", "https://github.com/micyo202/lion", "micyo202@163.com"))
                .version("2.0.2")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("BearerToken", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(securityReferences())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }

    private List<SecurityReference> securityReferences() {
        AuthorizationScope authorizationScope = new AuthorizationScope("all", "All scope is trusted");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("BearerToken", authorizationScopes));
    }
}