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
package com.lion.demo.provider;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * ProviderDemoApplication
 * 服务提供者demo
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/01/05
 */
@SpringCloudApplication
@EnableFeignClients
@EnableKnife4j
//@EnableTransactionManagement
//@MapperScan(basePackages = {"com.lion.common.**.mapper", "com.lion.demo.provider.**.mapper"})
@MapperScan("com.lion.**.mapper")
//@ComponentScan(basePackages = {"com.lion.common", "com.lion.demo.provider"})
@ComponentScan("com.lion")
public class ProviderDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderDemoApplication.class, args);
    }
}
