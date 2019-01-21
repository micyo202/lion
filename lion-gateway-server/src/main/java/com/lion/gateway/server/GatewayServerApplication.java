package com.lion.gateway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

/**
 * GatewayServerApplication
 * 路由网关
 *
 * @author Yanzheng 严正
 * @date 2019/01/07
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@SpringBootApplication
@RestController
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/get")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("http://localhost:8701/hello"))
                .build();
    }

}
