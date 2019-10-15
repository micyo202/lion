package com.lion.gateway.server.filter;

import com.lion.common.constant.ResponseStatus;
import com.lion.common.entity.Result;
import com.lion.common.util.JsonUtil;
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

        ServerHttpResponse response = exchange.getResponse();

        // 从请求头信息获取 access_token 进行检查
        String accessToken = exchange.getRequest().getQueryParams().getFirst(ACCESS_TOKEN);
        if (StringUtils.isEmpty(accessToken)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            String jsonString = JsonUtil.jsonObj2Str(Result.failure(ResponseStatus.UNAUTHORIZED.code(), "Token 不能为空"));
            return getVoidMono(response, jsonString);
        }

        // 检查 access_token 的有效性
        final String formatKey = String.format("access:%s", accessToken);
        final Boolean hasKey = stringRedisTemplate.hasKey(formatKey);
        if (!hasKey) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            String jsonString = JsonUtil.jsonObj2Str(Result.failure(ResponseStatus.UNAUTHORIZED.code(), "无效的 Token"));
            return getVoidMono(response, jsonString);
        }

        final Long expire = stringRedisTemplate.getExpire(formatKey);
        if (0 >= expire) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            String jsonString = JsonUtil.jsonObj2Str(Result.failure(ResponseStatus.UNAUTHORIZED.code(), "Token 已过期"));
            return getVoidMono(response, jsonString);
        }

        log.info("进入 lion-gateway-server 服务，执行 TokenFilter 过滤器，检查 Token 完成");
        return chain.filter(exchange);

    }

    private Mono<Void> getVoidMono(ServerHttpResponse response, String jsonString) {
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        byte[] datas = jsonString.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(datas);
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        /**
         * 值越大，优先级越低
         */
        return 10;
    }

}
