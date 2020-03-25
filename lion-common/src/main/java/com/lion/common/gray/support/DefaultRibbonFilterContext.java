package com.lion.common.gray.support;

import com.lion.common.constant.GrayConstant;
import com.lion.common.gray.api.RibbonFilterContext;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * DefaultRibbonFilterContext
 * 默认灰度负载过滤上下文实现类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/09/09
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class DefaultRibbonFilterContext implements RibbonFilterContext {

    private final Map<String, String> attributes = new HashMap<>();

    @Override
    public RibbonFilterContext add(String key, String value) {
        // 若version版本号为空，则赋值默认版本号
        if (key.equals(GrayConstant.VERSION) && StringUtils.isEmpty(value)) {
            value = GrayConstant.DEFAULT_VERSION;
        }
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
