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
 * ResponseCode
 * response响应状态码
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/03/30
 * Copyright 2020 Yanzheng. All rights reserved.
 */
public interface ResponseCode {

    /**
     * Success
     * 成功
     */
    int SUCCESS = 200;

    /**
     * Failure
     * 失败
     */
    int FAILURE = 500;

    /**
     * Bad Request
     * 请求错误
     */
    int BAD_REQUEST = 400;

    /**
     * Unauthorized
     * 未认证
     */
    int UNAUTHORIZED = 401;

    /**
     * Forbidden
     * 无权限
     */
    int FORBIDDEN = 403;

    /**
     * Not Found
     * 请求不存在
     */
    int NOT_FOUND = 404;

    /**
     * Method Not Allowed
     * 方法不允许
     */
    int METHOD_NOT_ALLOWED = 405;

    /**
     * Request Timeout
     * 请求超时
     */
    int REQUEST_TIMEOUT = 408;

    /**
     * Too Many Requests
     * 请求太多
     */
    int TOO_MANY_REQUESTS = 429;


}
