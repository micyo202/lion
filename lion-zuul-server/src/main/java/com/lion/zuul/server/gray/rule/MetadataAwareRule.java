package com.lion.zuul.server.gray.rule;

import com.lion.zuul.server.gray.predicate.DiscoveryEnabledPredicate;
import com.lion.zuul.server.gray.predicate.MetadataAwarePredicate;

public class MetadataAwareRule extends DiscoveryEnabledRule {

    public MetadataAwareRule() {
        this(new MetadataAwarePredicate());
    }

    public MetadataAwareRule(DiscoveryEnabledPredicate predicate) {
        super(predicate);
    }

}
