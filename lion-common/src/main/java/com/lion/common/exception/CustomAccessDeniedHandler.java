package com.lion.common.exception;

import com.lion.common.constant.ResponseCode;
import com.lion.common.entity.Result;
import com.lion.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CustomAccessDeniedHandler
 * 自定义无权限的响应处理
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/09/27
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        log.error(accessDeniedException.getMessage());

        //response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(JsonUtil.jsonObj2Str(Result.failure(ResponseCode.FORBIDDEN, "权限不足无法访问")));
    }

}
