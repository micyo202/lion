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

import com.lion.common.properties.RedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * RedissonConfig
 * 分布式锁配置
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/3/24
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
@Slf4j
public class RedissonConfig {

    private static final String REDIS_PREFIX = "redis://";

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public RedissonClient redissonClient() {

        log.info("初始化 Redisson");

        Config config = new Config();

        /**
         * 单机模式
         */
        if (StringUtils.isNotBlank(redisProperties.getHost())) {
            SingleServerConfig singleServerConfig = config.useSingleServer()
                    .setAddress(REDIS_PREFIX + redisProperties.getHost() + ":" + redisProperties.getPort());
            if (redisProperties.getTimeout() > 0) {
                singleServerConfig.setTimeout(redisProperties.getTimeout());
            }
            if (redisProperties.getDatabase() > 0) {
                singleServerConfig.setDatabase(redisProperties.getDatabase());
            }
            if (StringUtils.isNotBlank(redisProperties.getPassword())) {
                singleServerConfig.setPassword(redisProperties.getPassword());
            }
        }

        /**
         * 集群模式
         */
        if (null != redisProperties.getCluster() && CollectionUtils.isNotEmpty(redisProperties.getCluster().getNodes())) {
            List<String> clusterNodes = new ArrayList<>();
            //String[] nodes = redisProperties.getCluster().getNodes().split(",");
            //Arrays.stream(nodes).forEach((node) -> clusterNodes.add(node.startsWith(REDIS_PREFIX) ? node : REDIS_PREFIX + node));
            redisProperties.getCluster().getNodes()
                    .forEach(node -> clusterNodes.add(node.startsWith(REDIS_PREFIX) ? node : REDIS_PREFIX + node));
            ClusterServersConfig clusterServersConfig = config.useClusterServers()
                    .addNodeAddress(clusterNodes.toArray(new String[clusterNodes.size()]));
            if (redisProperties.getTimeout() > 0) {
                clusterServersConfig.setTimeout(redisProperties.getTimeout());
            }
            if (StringUtils.isNotBlank(redisProperties.getPassword())) {
                clusterServersConfig.setPassword(redisProperties.getPassword());
            }
        }

        /**
         * 哨兵模式
         */
        if (null != redisProperties.getSentinel() && StringUtils.isNotBlank(redisProperties.getSentinel().getMaster())) {
            List<String> sentinelNodes = new ArrayList<>();
            redisProperties.getSentinel().getNodes()
                    .forEach(node -> sentinelNodes.add(node.startsWith(REDIS_PREFIX) ? node : REDIS_PREFIX + node));
            SentinelServersConfig sentinelServersConfig = config.useSentinelServers()
                    .addSentinelAddress(sentinelNodes.toArray(new String[0]))
                    .setMasterName(redisProperties.getSentinel().getMaster());
            if (redisProperties.getTimeout() > 0) {
                sentinelServersConfig.setTimeout(redisProperties.getTimeout());
            }
            if (StringUtils.isNotBlank(redisProperties.getPassword())) {
                sentinelServersConfig.setPassword(redisProperties.getPassword());
            }
        }

        return Redisson.create(config);
    }

}
