package com.lion.common.gray.interceptor;

import com.lion.common.constant.GrayConstant;
import com.lion.common.gray.support.RibbonFilterContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * GrayHttpRequestInterceptor
 * RestTemplate拦截器
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/3/19
 * Copyright 2020 Yanzheng. All rights reserved.
 */
public class GrayHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest servletRequest = attributes.getRequest();

        // 设置请求头header信息
        Enumeration<String> headerNames = servletRequest.getHeaderNames();
        if (null != headerNames) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String value = servletRequest.getHeader(name);
                // 若version版本号为空，则赋值默认版本号
                if (name.equals(GrayConstant.VERSION) && StringUtils.isEmpty(value)) {
                    value = GrayConstant.DEFAULT_VERSION;
                }
                request.getHeaders().add(name, value);
            }
        }

        // 设置灰度版本
        String version = servletRequest.getHeader(GrayConstant.VERSION);
        RibbonFilterContextHolder.getCurrentContext().add(GrayConstant.VERSION, version);

        return execution.execute(request, body);
    }

}
