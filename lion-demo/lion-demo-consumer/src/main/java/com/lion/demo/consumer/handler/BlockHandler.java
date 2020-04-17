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
package com.lion.demo.consumer.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lion.common.constant.ResponseCode;
import com.lion.common.entity.Result;

/**
 * BlockHandler
 * sentinel流量控制自定义方法
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/08/19
 */
public class BlockHandler {

    public static Result sentinelBlockHandler(BlockException e) {
        e.printStackTrace();
        return Result.failure(ResponseCode.TOO_MANY_REQUESTS, "限流控制（Sentinel is blocked...）");
    }

}