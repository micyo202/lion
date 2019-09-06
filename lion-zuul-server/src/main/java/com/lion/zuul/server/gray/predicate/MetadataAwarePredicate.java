package com.lion.zuul.server.gray.predicate;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.lion.zuul.server.gray.api.RibbonFilterContext;
import com.lion.zuul.server.gray.support.RibbonFilterContextHolder;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * MetadataAwarePredicate
 * 灰度元数据适配断言类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/08/06
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class MetadataAwarePredicate extends DiscoveryEnabledPredicate {

    @Override
    protected boolean apply(NacosServer server) {

        final RibbonFilterContext context = RibbonFilterContextHolder.getCurrentContext();
        final Set<Map.Entry<String, String>> attributes = Collections.unmodifiableSet(context.getAttributes().entrySet());
        final Map<String, String> metadata = server.getMetadata();
        return metadata.entrySet().containsAll(attributes);
    }

}