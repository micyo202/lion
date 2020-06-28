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
package com.lion.common.constant;

/**
 * ResponseStatus
 * response响应状态常枚举常量
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/09/29
 */
public enum ResponseStatus {

    /**
     * Success
     * 成功
     */
    SUCCESS(ResponseCode.SUCCESS, "Success"),

    /**
     * Failure
     * 失败
     */
    FAILURE(ResponseCode.FAILURE, "Failure"),

    /**
     * Bad Request
     * 请求错误
     */
    BAD_REQUEST(ResponseCode.BAD_REQUEST, "Bad Request"),

    /**
     * Unauthorized
     * 未认证
     */
    UNAUTHORIZED(ResponseCode.UNAUTHORIZED, "Unauthorized"),

    /**
     * Forbidden
     * 无权限
     */
    FORBIDDEN(ResponseCode.FORBIDDEN, "Forbidden"),

    /**
     * Not Found
     * 请求不存在
     */
    NOT_FOUND(ResponseCode.NOT_FOUND, "Not Found"),

    /**
     * Method Not Allowed
     * 方法不允许
     */
    METHOD_NOT_ALLOWED(ResponseCode.METHOD_NOT_ALLOWED, "Method Not Allowed"),

    /**
     * Request Timeout
     * 请求超时
     */
    REQUEST_TIMEOUT(ResponseCode.REQUEST_TIMEOUT, "Request Timeout"),

    /**
     * Too Many Requests
     * 请求太多
     */
    TOO_MANY_REQUESTS(ResponseCode.TOO_MANY_REQUESTS, "Too Many Requests");

    /**
     * 代码
     */
    private final int code;

    /**
     * 信息
     */
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
