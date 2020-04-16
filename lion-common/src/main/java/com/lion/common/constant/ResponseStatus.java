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
