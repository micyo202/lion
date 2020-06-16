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
package com.lion.demo.consumer.feign.fallback;

import com.lion.common.result.Result;
import com.lion.demo.consumer.feign.ProviderDemoFeignClient;
import org.springframework.stereotype.Service;

/**
 * ProviderDemoClientFeignFallback
 * 服务调用失败处理类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/01/05
 */
@Service
public class ProviderDemoFeignClientFallback implements ProviderDemoFeignClient {

    @Override
    public Result<String> initFromProvider() {
        return Result.failure("FeignClient -> 调用服务端提供者：'lion-demo-provider'请求'/init'失败");
    }

    @Override
    public Result<String> hiFromProvider(String name) {
        return Result.failure("FeignClient -> 调用服务端提供者：'lion-demo-provider'请求'/hi'失败");
    }

    @Override
    public Result<String> deductFromProvider(String productCode, int count) {
        return Result.failure("FeignClient -> 调用服务端提供者：'lion-demo-provider'请求'/temp/product/deduct'失败");
    }
}