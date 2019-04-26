package com.lion.zuul.server.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * PreFilter
 * 前置拦截器
 *
 * @author Yanzheng 严正
 * @date 2019/01/02
 * Copyright 2019 Yanzheng. All rights reserved.
 */
//@Component
public class PreFilter extends ZuulFilter {
    @Override
    public String filterType() {
        /**
         * pre：路由之前
         * routing：路由之时
         * post： 路由之后
         * error：发送错误调用
         */
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 5; // 过滤器执行顺序，数字越小，优先级越高，越靠前,这里设置为 0
    }

    @Override
    public boolean shouldFilter() {
        return true;    // 返回 true，拦截所有 URL
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("进入lion-zuul-server服务，执行PreFilter 前置拦截器");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        System.out.println(request);
        return null;
    }
}
