package com.lion.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * CustomOAuth2Exception
 * 自定义认证异常类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/09/27
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@JsonSerialize(using = CustomOAuth2ExceptionSerializer.class)
public class CustomOAuth2Exception extends OAuth2Exception {

    private int code;

    CustomOAuth2Exception(int code, String msg) {
        super(msg);
        this.code = code;
    }

    @Override
    public int getHttpErrorCode() {
        return this.code;
    }

}
