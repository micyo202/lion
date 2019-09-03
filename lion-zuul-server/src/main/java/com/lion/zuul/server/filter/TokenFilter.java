package com.lion.zuul.server.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lion.common.entity.Result;
import com.lion.zuul.server.constant.FilterConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;

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
//@Component
public class TokenFilter extends ZuulFilter {
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
        return FilterConstants.PRE_FILTER_ORDER + 10;
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

        // token拦截器，检查token是否存在
        String token = request.getParameter("access_token");
        if (StringUtils.isEmpty(token)) {
            System.out.println("进入lion-zuul-server服务，执行TokenFilter，Token为空！");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonString = objectMapper.writeValueAsString(Result.failure(401, "token为空，无权访问！"));
                requestContext.getResponse().setContentType("application/json;charset=UTF-8");
                requestContext.setResponseBody(jsonString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        System.out.println("进入lion-zuul-server服务，执行TokenFilter，Token is OK!");
        return null;
    }
}