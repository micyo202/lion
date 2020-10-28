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
package com.lion.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.lion.gateway.filter.GrayFilter;
import com.lion.gateway.filter.TokenFilter;
import com.lion.gateway.handler.CustomExceptionHandler;
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
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/09/06
 */
@Configuration
public class SentinelConfig {

    private static final int COUNT = 10;
    private static final int INTERVAL_SEC = 1;

    final List<ViewResolver> viewResolvers;
    final ServerCodecConfigurer serverCodecConfigurer;

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
