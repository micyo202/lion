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
package com.lion.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * RedisProperties
 * Redis 配置属性
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2021/6/23
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedisProperties {

    /**
     * 单机配置
     */
    private String host;
    private String port = "6379";
    private String password;
    private int timeout = 30 * 1000;
    private int database = 0;

    /**
     * 集群配置
     */
    private RedisClusterProperties cluster;

    /**
     * 哨兵配置
     */
    private RedisSentinelProperties sentinel;
}
