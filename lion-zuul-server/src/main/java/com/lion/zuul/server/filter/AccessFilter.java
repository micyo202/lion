package com.lion.zuul.server.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * AccessFilter
 * 灰度发布控制过滤器
 *
 * @author Yanzheng
 * @date 2019/04/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
//@Component
public class AccessFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 5;
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
        // 灰度发布示例
        if (request.getParameter("tag") != null) {
            // put the serviceId in `RequestContext`
            RibbonFilterContextHolder.getCurrentContext()
                    .add("tag", "v1.0");
        } else {
            RibbonFilterContextHolder.getCurrentContext()
                    .add("tag", "v2.0");
        }

        return null;
    }

}