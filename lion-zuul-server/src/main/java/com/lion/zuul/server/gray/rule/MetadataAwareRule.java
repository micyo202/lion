package com.lion.zuul.server.gray.rule;

import com.lion.zuul.server.gray.predicate.DiscoveryEnabledPredicate;
import com.lion.zuul.server.gray.predicate.MetadataAwarePredicate;

/**
 * MetadataAwareRule
 * 灰度元数据适配规则类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/08/06
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class MetadataAwareRule extends DiscoveryEnabledRule {

    public MetadataAwareRule() {
        this(new MetadataAwarePredicate());
    }

    public MetadataAwareRule(DiscoveryEnabledPredicate predicate) {
        super(predicate);
    }

}
