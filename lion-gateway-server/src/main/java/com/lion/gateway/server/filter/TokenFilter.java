package com.lion.gateway.server.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lion.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * TokenFilter
 * token 过滤器
 *
 * @author Yanzheng
 * @date 2019/09/04
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Slf4j
public class TokenFilter implements GlobalFilter, Ordered {

    private static final String OAUTH_URL = "/oauth/";
    private static final String ACCESS_TOKEN = "access_token";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 获取当前请求url，若为 oauth/token 则不检查 access_token
        String requestUrl = exchange.getRequest().getURI().toString();
        if (requestUrl.contains(OAUTH_URL)) {
            return chain.filter(exchange);
        }

        // 从请求头信息获取 access_token 进行检查
        String accessToken = exchange.getRequest().getQueryParams().getFirst(ACCESS_TOKEN);
        if (StringUtils.isEmpty(accessToken)) {
            log.info("进入 lion-gateway-server 服务，执行 TokenFilter 过滤器，access_token 为空！");
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            String jsonString = null;
            try {
                jsonString = new ObjectMapper().writeValueAsString(Result.failure(401, "access_token 为空，无权访问！"));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            byte[] datas = jsonString.getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(datas);
            return response.writeWith(Mono.just(buffer));
        }

        // 检查 access_token 的有效性
        final String formatKey = String.format("access:%s", accessToken);
        final Boolean hasKey = stringRedisTemplate.hasKey(formatKey);
        if (!hasKey) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            String jsonString = null;
            try {
                jsonString = new ObjectMapper().writeValueAsString(Result.failure(401, "access_token 不存在，无权访问！"));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            byte[] datas = jsonString.getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(datas);
            return response.writeWith(Mono.just(buffer));
        }

        final Long expire = stringRedisTemplate.getExpire(formatKey);
        if (0 >= expire) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            String jsonString = null;
            try {
                jsonString = new ObjectMapper().writeValueAsString(Result.failure(401, "access_token 已失效，无权访问！"));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            byte[] datas = jsonString.getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(datas);
            return response.writeWith(Mono.just(buffer));
        }

        log.info("进入 lion-gateway-server 服务，执行 TokenFilter 过滤器，access_token 检查成功!");
        return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        /**
         * 值越大，优先级越低
         */
        return 10;
    }
}
