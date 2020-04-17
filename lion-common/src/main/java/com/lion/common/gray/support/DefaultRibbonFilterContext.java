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
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/09/09
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
