/**
 *   Copyright 2019 Yanzheng (https://github.com/micyo202). All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.lion.common.exception;

import com.lion.common.constant.ResponseCode;
import com.lion.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler
 * 全局异常处理类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/07/17
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 声明要捕获的异常
     */
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {

        Result result;

        if (e instanceof LionException) {
            LionException lionException = (LionException) e;
            result = Result.failure(lionException.getCode(), lionException.getMessage());
        } else if (e instanceof InvalidTokenException) {
            result = Result.failure(ResponseCode.UNAUTHORIZED, "无效的 Access Token");
        } else if (e instanceof InvalidGrantException) {
            result = Result.failure(ResponseCode.UNAUTHORIZED, "无效的 Refresh Token");
        } else if (e instanceof AccessDeniedException) {
            result = Result.failure(ResponseCode.FORBIDDEN, "权限不足无法访问");
        } else {
            log.error("系统异常", e);
            result = Result.failure(e.getMessage());
        }

        return result;
    }

}
