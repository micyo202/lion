package com.lion.zuul.server.constant;

/**
 * FilterConstants
 * 过滤器先后顺序号常量
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/08/19
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class FilterConstants {

    /**
     * 前置过滤器序号
     */
    public static final int PRE_FILTER_ORDER = 10000;

    /**
     * 后置过滤器序号
     */
    public static final int POST_FILTER_ORDER = 1000;

    /**
     * 错误过滤器序号
     */
    public static final int ERROR_FILTER_ORDER = -1;

}
