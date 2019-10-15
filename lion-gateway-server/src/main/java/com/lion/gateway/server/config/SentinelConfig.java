package com.lion.gateway.server.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.lion.gateway.server.filter.GrayFilter;
import com.lion.gateway.server.filter.TokenFilter;
import com.lion.gateway.server.handler.CustomExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * SentinelConfig
 * 哨兵 Sentinel 配置类
 *
 * @author Yanzheng
 * @date 2019/09/06
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Configuration
public class SentinelConfig {

    private static final int COUNT = 2;
    private static final int INTERVAL_SEC = 1;

    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public SentinelConfig(ObjectProvider<List<ViewResolver>> viewResolversProvider, ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    /*
    // 配置SentinelGatewayBlockExceptionHandler，限流后异常处理配置SentinelGatewayBlockExceptionHandler，限流后异常处理（Sentinel自带，不使用）
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        // Register the block exception handler for Spring Cloud Gateway.
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }
    */

    /**
     * 自定义异常处理，替换 SentinelGatewayBlockExceptionHandler
     */
    @Primary
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                                             ServerCodecConfigurer serverCodecConfigurer) {
        CustomExceptionHandler customCallbackExceptionHandler = new CustomExceptionHandler();
        customCallbackExceptionHandler.setViewResolvers(viewResolversProvider.getIfAvailable(Collections::emptyList));
        customCallbackExceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        customCallbackExceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
        return customCallbackExceptionHandler;
    }

    /**
     * 配置SentinelGatewayFilter
     */
    @Bean
    @Order(-1)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    /**
     * 自定义 token 过滤器
     */
    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

    /**
     * 灰度 gray 过滤器
     */
    @Bean
    public GrayFilter grayFilter() {
        return new GrayFilter();
    }

    @PostConstruct
    public void doInit() {
        initGatewayRules();
    }

    /**
     * 初始化限流规则（或在 dashboard 中配置）
     */
    private void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(new GatewayFlowRule("lion-auth")
                // 限流阈值QPS
                .setCount(COUNT)
                // 统计时间窗口，单位是秒，默认是 1 秒
                .setIntervalSec(INTERVAL_SEC)
        );
        rules.add(new GatewayFlowRule("lion-upms")
                .setCount(COUNT)
                .setIntervalSec(INTERVAL_SEC)
        );
        rules.add(new GatewayFlowRule("lion-id")
                .setCount(COUNT)
                .setIntervalSec(INTERVAL_SEC)
        );
        rules.add(new GatewayFlowRule("lion-bigdata")
                .setCount(COUNT)
                .setIntervalSec(INTERVAL_SEC)
        );
        rules.add(new GatewayFlowRule("lion-blockchain")
                .setCount(COUNT)
                .setIntervalSec(INTERVAL_SEC)
        );
        rules.add(new GatewayFlowRule("lion-demo-provider")
                .setCount(COUNT)
                .setIntervalSec(INTERVAL_SEC)
        );
        rules.add(new GatewayFlowRule("lion-demo-consumer")
                .setCount(COUNT)
                .setIntervalSec(INTERVAL_SEC)
        );
        GatewayRuleManager.loadRules(rules);
    }

}
