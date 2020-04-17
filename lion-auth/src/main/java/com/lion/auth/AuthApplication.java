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
package com.lion.auth;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.lion.common.amqp.AmqpReceiver;
import com.lion.common.amqp.AmqpSender;
import com.lion.common.config.ResourceServerConfig;
import com.lion.common.config.ScheduleConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * AuthApplication
 * OAuth2权限认证服务
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/2/5
 */
@SpringCloudApplication
@EnableFeignClients
@EnableKnife4j
@MapperScan("com.lion.auth.mapper")
@ComponentScan(basePackages = {"com.lion.auth", "com.lion.common"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = {ResourceServerConfig.class, ScheduleConfig.class, AmqpSender.class, AmqpReceiver.class})})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}