package com.lion.zuul.server.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * GrayFilter
 * 灰度发布拦截器
 *
 * @author Yanzheng
 * @date 2019/04/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
//@Component
public class GrayFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return RIBBON_ROUTING_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // a filter has already forwarded
        // a filter has already determined serviceId
        return !ctx.containsKey(FORWARD_TO_KEY)
                && !ctx.containsKey(SERVICE_ID_KEY);
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String version = request.getParameter("version");
        // 灰度发布示例
        if (null != version && !version.isEmpty()) {
            // put the serviceId in `RequestContext`
            RibbonFilterContextHolder.getCurrentContext()
                    .add("version", version);
        }

        return null;
    }

}