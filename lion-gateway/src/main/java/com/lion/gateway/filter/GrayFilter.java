package com.lion.gateway.filter;

import com.lion.common.constant.GrayConstant;
import com.lion.common.gray.support.RibbonFilterContextHolder;
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

    @Override
    public int getOrder() {
        /**
         * 值越大，优先级越低
         */
        return 20;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求头header中的version版本号
        String version = exchange.getRequest().getHeaders().getFirst(GrayConstant.VERSION);
        RibbonFilterContextHolder.getCurrentContext().add(GrayConstant.VERSION, version);

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            RibbonFilterContextHolder.getCurrentContext().remove(GrayConstant.VERSION);
        }));
    }

}
