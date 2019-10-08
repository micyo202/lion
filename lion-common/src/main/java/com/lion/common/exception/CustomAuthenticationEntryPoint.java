package com.lion.common.exception;

import com.lion.common.constant.ResponseStatus;
import com.lion.common.entity.Result;
import com.lion.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CustomAuthenticationEntryPoint
 * 自定义未授权的响应处理
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/09/26
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        log.error(authException.getMessage());

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        Throwable cause = authException.getCause();
        if (cause instanceof InvalidTokenException) {
            response.getWriter().print(JsonUtil.jsonObj2Str(Result.failure(ResponseStatus.UNAUTHORIZED.code(), "无效的 Token")));
        } else {
            response.getWriter().print(JsonUtil.jsonObj2Str(Result.failure(ResponseStatus.UNAUTHORIZED.code(), "尚未认证无法访问")));
        }

        /*
        if (isAjaxRequest(request)) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), authException.getMessage());
        } else {
            response.sendRedirect("/login");
        }
        */

    }

    /*
    private static boolean isAjaxRequest(HttpServletRequest request) {
        if (request.getHeader("accept").indexOf("application/json") > -1
                || (request.getHeader("X-Requested-With") != null
                && request.getHeader("X-Requested-With").equals("XMLHttpRequest"))) {
            return true;
        }
        return false;
    }
    */

}
