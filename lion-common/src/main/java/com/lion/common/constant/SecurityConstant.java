package com.lion.common.constant;

/**
 * SecurityConstant
 * 安全配置常量
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/3/17
 * Copyright 2020 Yanzheng. All rights reserved.
 */
public interface SecurityConstant {

    String OAUTH_URL = "/oauth/";

    String ACCESS_TOKEN = "Authorization";

    String BEARER_PREFIX = "Bearer ";

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
