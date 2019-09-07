package com.lion.zuul.server.filter;

import com.lion.zuul.server.constant.FilterOrderConstants;
import com.lion.zuul.server.gray.support.RibbonFilterContextHolder;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * GrayFilter
 * 灰度拦截器（打开 @Component 注解生效）
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
//@Component
public class GrayFilter extends ZuulFilter {

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
        /**
         * 过滤器执行顺序，数字越小，优先级越高，越靠前
         */
        return FilterOrderConstants.PRE_FILTER_ORDER + 20;
    }

    @Override
    public boolean shouldFilter() {
        /**
         * 返回 true，拦截所有 URL
         */
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        // 灰度拦截器
        String version = request.getParameter("version");
        if (null != version && !version.isEmpty()) {
            // put the serviceId in `RequestContext`
            RibbonFilterContextHolder.getCurrentContext()
                    .add("version", version);
        }

        return null;
    }

}