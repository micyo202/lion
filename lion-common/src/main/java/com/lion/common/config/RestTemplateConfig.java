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
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * RestTemplateConfig
 * RestTemplate 配置
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/3/24
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 设置请求连接超时时间（毫秒）
     */
    private static final int CONNECTION_REQUEST_TIMEOUT = 30 * 1000;

    /**
     * 设置连接超时时间（毫秒）
     */
    private static final int CONNECT_TIMEOUT = 90 * 1000;

    /**
     * 设置读取超时时间（毫秒）
     */
    private static final int READ_TIMEOUT = 90 * 1000;

    @Bean
    @LoadBalanced
    @SentinelRestTemplate
    public RestTemplate restTemplate() {

        /**
         * 使用 HttpClient 请求
         */
        HttpClient httpClient = HttpClientBuilder
                .create()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT);
        factory.setConnectTimeout(CONNECT_TIMEOUT);
        factory.setReadTimeout(READ_TIMEOUT);
        factory.setHttpClient(httpClient);

        RestTemplate restTemplate = new RestTemplate(factory);
        // 将自定义的 ClientHttpRequestInterceptor 添加到 RestTemplate 中，可添加多个
        restTemplate.setInterceptors(Collections.singletonList(new GrayHttpRequestInterceptor()));
        return restTemplate;
    }

}
