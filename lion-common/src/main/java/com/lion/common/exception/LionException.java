package com.lion.common.exception;

import com.lion.common.constant.ResponseStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LionException
 * 自定义异常类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/07/17
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Data
@NoArgsConstructor
public class LionException extends RuntimeException {

    /**
     * 错误码
     */
    private int code;

    public LionException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public LionException(String msg) {
        super(msg);
        this.code = ResponseStatus.FAILURE.code();
    }

}
