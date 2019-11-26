package com.lion.demo.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lion.common.base.controller.BaseController;
import com.lion.common.entity.Result;
import com.lion.common.lock.annotation.Locker;
import com.lion.demo.consumer.client.ProviderDemoClient;
import com.lion.demo.consumer.handler.BlockHandler;
import com.netflix.ribbon.hystrix.FallbackHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

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
        return Result.success("Consumer -> version: " + version + ", port: " + port + ", foo: " + foo);
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
        log.info("ribbonHi 服务消费者 Consumer");
        return restTemplate.getForObject("http://lion-demo-provider/hi", String.class, name);
    }

    public String ribbonHiFallback(String name) {
        return "Ribbon Hi: '" + name + "', fallback sentinel";
    }

    @ApiOperation("sentinel流量控制接口")
    @GetMapping("/sentinel/block")
    @SentinelResource(value = "sentinelBlock", blockHandler = "sentinelBlockHandler", blockHandlerClass = BlockHandler.class)
    public Result sentinelBlock() {
        return Result.success("This is sentinel control service flow");
    }

    @ApiOperation("sentinel服务熔断、降级接口")
    @GetMapping("/sentinel/fallback")
    @SentinelResource(value = "sentinelFallback", fallback = "sentinelFallback", fallbackClass = FallbackHandler.class)
    public Result sentinelFallback() {
        throw new RuntimeException();
        //return Result.failure(500, "This is sentinel control service fallback");
    }

    @ApiOperation("分布式锁")
    @Locker
    @RequestMapping(value = "/lock", method = {RequestMethod.GET, RequestMethod.POST})
    public Result lock() {
        try {
            log.info("执行锁中业务逻辑");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.success("分布式锁方法执行成功");
    }

    @RequestMapping(value = "/rabbit/{flag}", method = {RequestMethod.GET, RequestMethod.POST})
    public Result rabbit(@PathVariable String flag) {
        return providerDemoClient.sendFromProvider(flag);
    }

    @ApiOperation("权限认证 - 无需 access_token 访问（产品查看接口）")
    @ApiParam(name = "id", value = "产品主键（无需 access_token）")
    @RequestMapping(value = "/product/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public Result getProduct(@PathVariable String id) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Result.success("无需 access_token 访问，Product id : " + id);
    }

    @ApiOperation("权限认证 - 需要 access_token 访问（订单查看接口）")
    @ApiParam(name = "id", value = "订单主键（需要 access_token）")
    @RequestMapping(value = "/order/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public Result getOrder(@PathVariable String id) {
        return Result.success("需要 access_token 才能访问，Order id : " + id);
    }

    @ApiOperation("角色控制 - 需要拥有admin角色")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //@PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.POST})
    public Result getAdmin() {
        return Result.success("当前用户，拥有Admin权限可访问");
    }

    @ApiOperation("角色控制 - 需拥有user角色")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/user", method = {RequestMethod.GET, RequestMethod.POST})
    public Result getUser() {
        return Result.success("当前用户，拥有User权限可访问......");
    }

    @ApiOperation("获取用户凭证信息")
    @RequestMapping(value = "/principle", method = {RequestMethod.GET, RequestMethod.POST})
    public Result getPrinciple(OAuth2Authentication oAuth2Authentication, Principal principal, Authentication authentication) {
        log.info(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        log.info(oAuth2Authentication.toString());
        log.info("principal.toString() " + principal.toString());
        log.info("principal.getName() " + principal.getName());
        log.info("authentication: " + authentication.getAuthorities().toString());
        return Result.success(oAuth2Authentication);
    }

    @PostMapping("/upload")
    public Result upload(HttpServletRequest request) {
        List<String> list = fileUpload(request);
        return Result.success(list);
    }

    @GetMapping("/download/{fileName}")
    public Result download(@PathVariable String fileName, HttpServletResponse response) {
        boolean res = fileDownload(fileName, response);
        return res ? Result.success() : Result.failure("文件下载失败");
    }

}
