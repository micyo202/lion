package com.lion.gateway.filter;

import com.lion.gateway.gray.support.RibbonFilterContextHolder;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * GrayFilter
 * 灰度过滤器
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/09/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class GrayFilter implements GlobalFilter, Ordered {

    private static final String KEY = "version";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 灰度拦截器
        String version = exchange.getRequest().getQueryParams().getFirst(KEY);
        if (null != version && !version.isEmpty()) {
            // add the version in 'RequestContext'
            RibbonFilterContextHolder.getCurrentContext().add(KEY, version);
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        /**
         * 值越大，优先级越低
         */
        return 20;
    }

}
