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
package com.lion.common.gray.predicate;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.lion.common.gray.api.RibbonFilterContext;
import com.lion.common.gray.support.RibbonFilterContextHolder;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * MetadataAwarePredicate
 * 灰度元数据适配断言类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/09/09
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
