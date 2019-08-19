package com.lion.common.exception;

/**
 * LionException
 * TODO
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/07/17
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class LionException extends RuntimeException {

    // 错误码
    private int code;

    public LionException() {
    }

    public LionException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
