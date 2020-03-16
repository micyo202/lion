package com.lion.common.constant;

/**
 * ResponseStatus
 * response响应状态常量类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/09/29
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public enum ResponseStatus {

    /**
     * Success
     * 成功
     */
    SUCCESS(200, "Success"),

    /**
     * Failure
     * 失败
     */
    FAILURE(500, "Failure"),

    /**
     * Bad Request
     * 请求错误
     */
    BAD_REQUEST(400, "Bad Request"),

    /**
     * Unauthorized
     * 未认证
     */
    UNAUTHORIZED(401, "Unauthorized"),

    /**
     * Forbidden
     * 无权限
     */
    FORBIDDEN(403, "Forbidden"),

    /**
     * Not Found
     * 请求不存在
     */
    NOT_FOUND(404, "Not Found"),

    /**
     * Method Not Allowed
     * 方法不允许
     */
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

    /**
     * Request Timeout
     * 请求超时
     */
    REQUEST_TIMEOUT(408, "Request Timeout"),

    /**
     * Too Many Requests
     * 请求太多
     */
    TOO_MANY_REQUESTS(429, "Too Many Requests");

    private final int code;

    private final String msg;

    ResponseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }
}
