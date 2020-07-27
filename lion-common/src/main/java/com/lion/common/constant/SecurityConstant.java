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
 * SecurityConstant
 * 安全配置常量
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/3/17
 */
public interface SecurityConstant {

    /**
     * 登录url
     */
    String LOGIN_URL = "/login";

    /**
     * 认证url
     */
    String OAUTH_URL = "/oauth/";

    /**
     * token 名称
     */
    String ACCESS_TOKEN = "Authorization";

    /**
     * bearer 前缀
     */
    String BEARER_PREFIX = "Bearer ";

    /**
     * access 前缀
     */
    String ACCESS_PREFIX = "access:";

    /**
     * 系统固定不进行认证，直接放行的URL，供WebSecurityConfig、ResourceServerConfig公用
     */
    String[] PATTERN_URLS = {
            "/actuator/**",
            "/druid/**",

            "/webjars/**",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/v2/api-docs-ext",
            "/swagger-ui.html",
            "/doc.html"
    };
}
