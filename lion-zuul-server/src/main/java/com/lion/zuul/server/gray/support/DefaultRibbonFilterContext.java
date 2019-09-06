package com.lion.zuul.server.gray.support;

import com.lion.zuul.server.gray.api.RibbonFilterContext;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * DefaultRibbonFilterContext
 * 默认灰度负载过滤上下文实现类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/08/06
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class DefaultRibbonFilterContext implements RibbonFilterContext {

    private final Map<String, String> attributes = new HashMap<>();

    @Override
    public RibbonFilterContext add(String key, String value) {
        attributes.put(key, value);
        return this;
    }

    @Override
    public String get(String key) {
        return attributes.get(key);
    }

    @Override
    public RibbonFilterContext remove(String key) {
        attributes.remove(key);
        return this;
    }

    @Override
    public Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

}
