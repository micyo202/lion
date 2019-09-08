package com.lion.zuul.server.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lion.common.entity.Result;
import com.lion.zuul.server.constant.FilterOrderConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * TokenFilter
 * token拦截器，检查token是否存在（打开 @Component 注解生效）
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/26
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Component
@Slf4j
public class TokenFilter extends ZuulFilter {

    private static final String OAUTH_URL = "/oauth/";
    private static final String ACCESS_TOKEN = "access_token";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
        return FilterOrderConstants.PRE_FILTER_ORDER + 10;
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

        // 获取当前请求url，若为 oauth/token 则不检查 access_token
        String requestUrl = request.getRequestURI();
        if (requestUrl.contains(OAUTH_URL)) {
            return null;
        }

        // 从请求头信息获取 access_token 进行检查
        String accessToken = request.getParameter(ACCESS_TOKEN);
        if (StringUtils.isEmpty(accessToken)) {
            log.info("进入 lion-gateway-server 服务，执行 TokenFilter 过滤器，access_token 为空！");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonString = objectMapper.writeValueAsString(Result.failure(401, "access_token 为空，无权访问！"));
                requestContext.getResponse().setContentType("application/json;charset=UTF-8");
                requestContext.setResponseBody(jsonString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        // 检查 access_token 的有效性
        final String formatKey = String.format("access:%s", accessToken);
        // token 是否存在
        final Boolean hasKey = stringRedisTemplate.hasKey(formatKey);
        if (!hasKey) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonString = objectMapper.writeValueAsString(Result.failure(401, "access_token 不存在，无权访问！"));
                requestContext.getResponse().setContentType("application/json;charset=UTF-8");
                requestContext.setResponseBody(jsonString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        // token 是否有效
        final Long expire = stringRedisTemplate.getExpire(formatKey);
        if (0 >= expire) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonString = objectMapper.writeValueAsString(Result.failure(401, "access_token 已失效，无权访问！"));
                requestContext.getResponse().setContentType("application/json;charset=UTF-8");
                requestContext.setResponseBody(jsonString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        log.info("进入 lion-gateway-server 服务，执行 TokenFilter 过滤器，access_token 检查成功!");
        return null;
    }

}