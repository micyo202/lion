package com.lion.zuul.server.filter;

import com.lion.zuul.server.constant.FilterOrderConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

/**
 * PostFilter
 * 后置拦截器（打开 @Component 注解生效）
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/01/02
 * Copyright 2019 Yanzheng. All rights reserved.
 */
//@Component
public class PostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        /**
         * pre：路由之前
         * routing：路由之时
         * post： 路由之后
         * error：发送错误调用
         */
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        /**
         * 过滤器执行顺序，数字越小，优先级越高，越靠前
         */
        return FilterOrderConstants.POST_FILTER_ORDER - 10;
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
        System.out.println("进入lion-zuul-server服务，执行PostFilter，设置Header值。");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletResponse response = requestContext.getResponse();
        response.setHeader("lion", "lion-zuul-server PostFilter");
        return null;
    }
}
