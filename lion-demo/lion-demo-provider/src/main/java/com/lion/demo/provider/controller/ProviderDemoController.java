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
package com.lion.demo.provider.controller;

import com.lion.common.base.controller.BaseController;
import com.lion.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * ProviderDemoController
 * 服务提供者示例代码
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/09/25
 */
@Api("服务提供者示例代码")
@RestController
@Slf4j
public class ProviderDemoController extends BaseController {

    @ApiOperation("初始化")
    @GetMapping("/init")
    public Result init() {
        return Result.success(applicationName + " -> port: " + port + ", version: " + version);
    }

    @ApiOperation("返回Hi文本内容")
    @ApiParam(name = "name", value = "名称", defaultValue = "lion")
    @RequestMapping(value = "/hi", method = {RequestMethod.GET, RequestMethod.POST})
    public Result hi(@RequestParam(defaultValue = "lion") String name) {
        log.info("Provider -> 服务提供者 /hi");
        return Result.success("Hi '" + name + "', I'm Provider, From port: " + port + ", version: " + version);
    }

}