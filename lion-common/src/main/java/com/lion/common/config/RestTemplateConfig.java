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
package com.lion.common.config;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.lion.common.gray.interceptor.GrayHttpRequestInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * RestTemplateConfig
 * RestTemplate 配置类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/3/24
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    @SentinelRestTemplate
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // 将自定义的 ClientHttpRequestInterceptor 添加到 RestTemplate 中，可添加多个
        restTemplate.setInterceptors(Collections.singletonList(new GrayHttpRequestInterceptor()));
        return restTemplate;
    }

}
