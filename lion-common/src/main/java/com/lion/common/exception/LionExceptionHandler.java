package com.lion.common.exception;

import com.lion.common.entity.Result;
import io.netty.channel.ChannelException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * LionExceptionHandler
 * TODO
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
    public Result serviceException(Exception e) {

        Result result;

        if (e instanceof ChannelException) {
            log.error("业务异常：" + e.getMessage());
            LionException lionException = (LionException) e;
            result = Result.failure(lionException.getCode(), lionException.getMessage());
        } else {    // 系统异常
            log.error("系统异常：" + e.getMessage());
            result = Result.failure(9999, e.getMessage());
        }

        log.info("执行完毕（异常）...");
        ThreadContext.remove("logId");

        e.printStackTrace();

        return result;
    }

}
