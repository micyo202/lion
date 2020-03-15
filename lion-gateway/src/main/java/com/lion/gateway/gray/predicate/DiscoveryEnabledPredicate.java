package com.lion.gateway.gray.predicate;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.PredicateKey;
import org.springframework.lang.Nullable;

/**
 * DiscoveryEnabledPredicate
 * 灰度发现启用断言抽象类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/09/09
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public abstract class DiscoveryEnabledPredicate extends AbstractServerPredicate {

    @Override
    public boolean apply(@Nullable PredicateKey input) {
        return input != null
                && input.getServer() instanceof NacosServer
                && apply((NacosServer) input.getServer());
    }

    protected abstract boolean apply(NacosServer server);

}
