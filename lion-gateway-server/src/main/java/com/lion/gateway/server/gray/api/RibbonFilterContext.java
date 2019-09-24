package com.lion.gateway.server.gray.api;

import java.util.Map;

/**
 * RibbonFilterContext
 * 灰度负载过滤上下文接口类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/09/09
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public interface RibbonFilterContext {

    RibbonFilterContext add(String key, String value);

    String get(String key);

    RibbonFilterContext remove(String key);

    Map<String, String> getAttributes();

}
