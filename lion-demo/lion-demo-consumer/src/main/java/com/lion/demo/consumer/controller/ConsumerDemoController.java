package com.lion.demo.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lion.common.entity.Result;
import com.lion.common.util.DateUtil;
import com.lion.demo.consumer.client.ProviderDemoClientFeign;
import com.lion.demo.consumer.mapper.TempMybatisCustomMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

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
public class ConsumerDemoController {

    /**
     * 自定义配置值
     */
    @Value("${foo}")
    private String foo;

    /**
     * 端口
     */
    @Value("${server.port}")
    private String port;

    @ApiOperation("初始化接口")
    @GetMapping("/init")
    public Result init() {
        return Result.success(foo + ", i'm from port: " + port);
    }

    /**
     * FeginClient服务请求
     */
    @Autowired
    ProviderDemoClientFeign providerDemoClientFeign;

    @ApiOperation("feign示例接口，返回Hi文本内容")
    @ApiParam(name = "name", value = "名称（默认lion）", defaultValue = "lion", required = true)
    @GetMapping("/feign/hi")
    public Result feignHi(String name) {
        return providerDemoClientFeign.hiFromProvider(name);
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

    /**
     * TempMybatis 自定义 Mapper 类
     */
    @Autowired
    private TempMybatisCustomMapper tempMybatisCustomMapper;

    @ApiOperation(value = "seata分布式事物接口", notes = "若要触发seate分布式事物回滚，插入数据条数大于3即可")
    @ApiParam(name = "num", value = "插入数据条数", required = true)
    @RequestMapping(value = "/seata/save/{num}", method = {RequestMethod.GET, RequestMethod.POST})
    //@GlobalTransactional
    public Result tc(@PathVariable int num) {

        if (num <= 0) {
            return Result.failure(500, "参数必须是大于0的数字");
        }

        Result result = providerDemoClientFeign.jpaSaveFromProvider(num);

        log.info("FeignClient 调用 provider 服务完成 reslut: {}", result);

        for (int i = 0; i < num; i++) {

            String randomId;
            if (i <= 3) {
                //正常插入
                randomId = UUID.randomUUID().toString().replace("-", "");
            } else {
                //超出范围长度，触发事物回滚
                randomId = UUID.randomUUID().toString();
            }

            String randomStr = Math.ceil(Math.random() * 100) + "";
            String randomName = "name-" + randomStr;
            String createTime = DateUtil.getCurrentDateTime();

            tempMybatisCustomMapper.insertTempMybatis(randomId, randomName, 99, 1, createTime);

        }

        return Result.success("seata分布式事务方法，执行完成");
    }

}
