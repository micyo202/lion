package com.lion.zuul.server.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.RateLimiter;
import com.lion.common.entity.Result;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * RateLimitFilter
 * 限流拦截器（采用 Sentinel 实现）
 *
 * @author Yanzheng 严正
 * @date 2019/01/02
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Deprecated
//@Component
public class RateLimitFilter extends ZuulFilter {

    // 塞入令牌
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        // 如果获取不到令牌，进行拦截
        if (!RATE_LIMITER.tryAcquire()) {
            System.out.println("进入lion-zuul-server服务，执行RateLimitFilter，获取不到令牌。");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.BAD_REQUEST.value());
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = null;
            try {
                jsonString = objectMapper.writeValueAsString(Result.failure(429, "前方拥挤，轻稍后再试！（流量控制）"));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            requestContext.getResponse().setContentType("application/json;charset=UTF-8");
            requestContext.setResponseBody(jsonString);
        }
        System.out.println("进入lion-zuul-server服务，执行RateLimitFilter，获取令牌成功！");
        return null;
    }
}
