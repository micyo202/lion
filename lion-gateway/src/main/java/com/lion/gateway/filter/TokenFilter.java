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
package com.lion.gateway.filter;

import com.lion.common.constant.ResponseCode;
import com.lion.common.constant.SecurityConstant;
import com.lion.common.result.Result;
import com.lion.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * access_token需请求时放在header中，格式'Authorization: Bearer token'
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/09/04
 */
@Slf4j
public class TokenFilter implements GlobalFilter, Ordered {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public int getOrder() {
        /**
         * 值越大，优先级越低
         */
        return 10;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 获取当前请求url，若为包含 /oauth/ 则不检查 access_token
        String requestUrl = exchange.getRequest().getURI().toString();
        if (requestUrl.contains(SecurityConstant.OAUTH_URL)) {
            return chain.filter(exchange);
        }

        ServerHttpResponse response = exchange.getResponse();

        // 从请求头信息获取 access_token 进行检查
        String accessToken = exchange.getRequest().getHeaders().getFirst(SecurityConstant.ACCESS_TOKEN);
        if (StringUtils.isEmpty(accessToken)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            String jsonString = JsonUtil.jsonObj2Str(Result.failure(ResponseCode.UNAUTHORIZED, "Access Token 不能为空"));
            return getVoidMono(response, jsonString);
        }

        // 判断 access_token 是否 Bearer  开头
        if (!accessToken.startsWith(SecurityConstant.BEARER_PREFIX)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            String jsonString = JsonUtil.jsonObj2Str(Result.failure(ResponseCode.UNAUTHORIZED, "Access Token 格式不正确"));
            return getVoidMono(response, jsonString);
        }

        // 格式化 access_token 前缀 Bearer  -> access:
        final String formatKey = accessToken.replace(SecurityConstant.BEARER_PREFIX, SecurityConstant.ACCESS_PREFIX);

        // 检查 access_token 的有效性
        final Boolean hasKey = stringRedisTemplate.hasKey(formatKey);
        if (!hasKey) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            String jsonString = JsonUtil.jsonObj2Str(Result.failure(ResponseCode.UNAUTHORIZED, "无效的 Access Token"));
            return getVoidMono(response, jsonString);
        }

        final Long expire = stringRedisTemplate.getExpire(formatKey);
        if (0 >= expire) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            String jsonString = JsonUtil.jsonObj2Str(Result.failure(ResponseCode.UNAUTHORIZED, "Access Token 已过期"));
            return getVoidMono(response, jsonString);
        }

        log.info("进入 lion-gateway 服务，执行 TokenFilter 过滤器，检查 Access Token 完成");
        return chain.filter(exchange);
    }

    private Mono<Void> getVoidMono(ServerHttpResponse response, String jsonString) {
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        byte[] datas = jsonString.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(datas);
        return response.writeWith(Mono.just(buffer));
    }

}
