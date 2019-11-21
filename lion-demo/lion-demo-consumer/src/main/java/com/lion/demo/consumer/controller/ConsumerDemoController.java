package com.lion.demo.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lion.common.base.controller.BaseController;
import com.lion.common.entity.Result;
import com.lion.demo.consumer.client.ProviderDemoClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * ConsumerDemoController
 * 服务消费者示例代码
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/01/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Api("服务消费者示例代码")
@RestController
@Slf4j
public class ConsumerDemoController extends BaseController {

    /**
     * 自定义配置值
     */
    @Value("${foo}")
    private String foo;

    @ApiOperation("初始化接口")
    @GetMapping("/init")
    public Result init() {
        return Result.success(foo + ", i'm from port: " + port);
    }

    /**
     * FeginClient服务请求
     */
    @Autowired
    private ProviderDemoClient providerDemoClient;

    @ApiOperation("feign示例接口，返回Hi文本内容")
    @ApiParam(name = "name", value = "名称（默认lion）", defaultValue = "lion", required = true)
    @GetMapping("/feign/hi")
    public Result feignHi(String name) {
        log.info("feignHi 服务消费者 Consumer");
        return providerDemoClient.hiFromProvider(name);
    }

    @Autowired
    RestTemplate restTemplate;

    @ApiOperation("ribbon示例接口，返回Hi文本内容")
    @ApiParam(name = "name", value = "名称（默认lion）", defaultValue = "lion", required = true)
    @GetMapping("/ribbon/hi")
    @SentinelResource(value = "ribbonHi", fallback = "ribbonHiFallback")
    public String ribbonHi(String name) {
        return restTemplate.getForObject("http://lion-demo-provider/hi", String.class, name);
    }

    public String ribbonHiFallback(String name) {
        return "Ribbon Hi: '" + name + "', fallback sentinel";
    }

}
