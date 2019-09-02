package com.lion.zuul.server.gray.predicate;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.PredicateKey;
import org.springframework.lang.Nullable;

public abstract class DiscoveryEnabledPredicate extends AbstractServerPredicate {

    @Override
    public boolean apply(@Nullable PredicateKey input) {
        return input != null
                && input.getServer() instanceof NacosServer
                && apply((NacosServer) input.getServer());
    }

    protected abstract boolean apply(NacosServer server);

}
