package com.lion.common.base.controller;

import com.lion.common.amqp.MessageSender;
import com.lion.common.info.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * BaseController
 * 通用控制器
 *
 * @author Yanzheng
 * @date 2019/11/19
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@RestController
public abstract class BaseController extends ApplicationObjectSupport {

    /**
     * 端口号
     */
    @Value("${server.port}")
    protected String port;

    /**
     * 应用名称（默认：lion-unknown）
     */
    @Value("${spring.application.name:lion-unknown}")
    protected String applicationName;

    /**
     * 版本，从gateway服务发起，用于灰度（如：http://localhost:8400/demo/consumer/gray?version=1.0）
     */
    @Value("${spring.cloud.nacos.discovery.metadata.version:1.0}")
    protected String version;

    /**
     * 认证服务地址（默认：http://localhost:8888/oauth/token）
     */
    @Value("${security.oauth2.client.access-token-uri:http://localhost:8888/oauth/token}")
    protected String authServer;

    /**
     * RabbitMQ 消息发送者
     */
    @Autowired
    protected MessageSender messageSender;

    /**
     * 根据名称获取bean对象
     *
     * @param name 名称
     */
    protected Object getBean(String name) {
        return this.getApplicationContext().getBean(name);
    }

    protected boolean isAjaxRequest(HttpServletRequest request) {
        if (!(request.getHeader("Accept").contains("application/json")
                || (request.getHeader("X-Requested-With") != null
                && request.getHeader("X-Requested-With").contains("XMLHttpRequest"))
                || "XMLHttpRequest".equalsIgnoreCase(request.getParameter("X_REQUESTED_WITH")))) {
            return false;
        }
        return true;
    }

    /**
     * 获取request
     */
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取response
     */
    protected HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取session
     */
    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 根据key获取request中属性值
     *
     * @param key 键
     */
    protected Object getRequestAttribute(String key) {
        return this.getRequest().getAttribute(key);
    }

    /**
     * 给request设置属性值
     *
     * @param key   键
     * @param value 值
     */
    protected void setRequestAttribute(String key, Object value) {
        this.getRequest().setAttribute(key, value);
    }

    /**
     * 健康检查
     */
    @GetMapping("/health")
    public String health() {

        UserInfo userInfo = new UserInfo();
        OsInfo osInfo = new OsInfo();
        JavaInfo javaInfo = new JavaInfo();
        JvmInfo jvmInfo = new JvmInfo();
        RuntimeInfo runtimeInfo = new RuntimeInfo();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[UserInfo]\n").append(userInfo.toString()).append("\n---------")
                .append("\n[OsInfo]\n").append(osInfo.toString()).append("\n---------")
                .append("\n[JavaInfo]\n").append(javaInfo.toString()).append("\n---------")
                .append("\n[JvmInfo]\n").append(jvmInfo.toString()).append("\n---------")
                .append("\n[RuntimeInfo]\n").append(runtimeInfo.toString()).append("\n");

        return stringBuilder.toString();
    }

}
