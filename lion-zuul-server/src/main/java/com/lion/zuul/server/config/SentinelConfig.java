package com.lion.zuul.server.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.ZuulBlockFallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulErrorFilter;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulPostFilter;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulPreFilter;
import com.lion.zuul.server.constant.FilterConstants;
import com.lion.zuul.server.fallback.CustomBlockFallbackProvider;
import com.netflix.zuul.ZuulFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * ZuulConfig
 * 流量哨兵 Sentinel 配置类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/08/16
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Configuration
public class SentinelConfig {

    @Bean
    public ZuulFilter sentinelZuulPreFilter() {
        return new SentinelZuulPreFilter(FilterConstants.PRE_FILTER_ORDER);
    }

    @Bean
    public ZuulFilter sentinelZuulPostFilter() {
        return new SentinelZuulPostFilter(FilterConstants.POST_FILTER_ORDER);
    }

    @Bean
    public ZuulFilter sentinelZuulErrorFilter() {
        return new SentinelZuulErrorFilter(FilterConstants.ERROR_FILTER_ORDER);
    }

    @PostConstruct
    public void doInit() {
        initFallback();
        initGatewayRules();
    }

    /**
     * 初始化限流规则（或在 dashboard 中配置）
     */
    private void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(new GatewayFlowRule("auth")
                .setCount(2)
                .setIntervalSec(1)
        );
        rules.add(new GatewayFlowRule("upms")
                .setCount(2)
                .setIntervalSec(1)
        );
        rules.add(new GatewayFlowRule("demo/provider")
                .setCount(2)
                .setIntervalSec(1)
        );
        GatewayRuleManager.loadRules(rules);
    }

    /**
     * 自定义错误回滚提示内容
     */
    private void initFallback() {
        ZuulBlockFallbackManager.registerProvider(new CustomBlockFallbackProvider());
    }

}
