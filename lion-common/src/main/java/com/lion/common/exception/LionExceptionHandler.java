package com.lion.common.exception;

import com.lion.common.constant.ResponseStatus;
import com.lion.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * LionExceptionHandler
 * 自定义全局异常处理类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/07/17
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@RestControllerAdvice
@Slf4j
public class LionExceptionHandler {

    /**
     * 声明要捕获的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {

        Result result;

        if (e instanceof LionException) {
            LionException lionException = (LionException) e;
            result = Result.failure(lionException.getCode(), lionException.getMessage());
        } else if (e instanceof InvalidTokenException) {
            result = Result.failure(ResponseStatus.UNAUTHORIZED.code(), "无效的 Token");
        } else if (e instanceof InvalidGrantException) {
            result = Result.failure(ResponseStatus.UNAUTHORIZED.code(), "无效的 Refresh Token");
        } else if (e instanceof AccessDeniedException) {
            result = Result.failure(ResponseStatus.FORBIDDEN.code(), "权限不足无法访问");
        } else {
            log.error("系统异常", e);
            result = Result.failure(e.getMessage());
        }

        return result;
    }

}
