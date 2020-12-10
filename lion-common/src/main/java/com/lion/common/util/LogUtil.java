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
package com.lion.common.util;

import com.lion.common.constant.LogConstant;
import org.slf4j.MDC;

/**
 * LogUtil
 * 日志工具类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/6/10
 */
public class LogUtil {

    private LogUtil() {}

    public static String getTraceId() {
        return getValueByKey(LogConstant.TRACE_ID);
    }

    public static void setTraceId(String traceId) {
        setValueByKey(LogConstant.TRACE_ID, traceId);
    }

    public static String getSpanId() {
        return getValueByKey(LogConstant.SPAN_ID);
    }

    public static void setSpanId(String spanId) {
        setValueByKey(LogConstant.SPAN_ID, spanId);
    }

    public static String getParentSpanId() {
        return getValueByKey(LogConstant.PARENT_SPAN_ID);
    }

    public static void setParentSpanId(String parentSpanId) {
        setValueByKey(LogConstant.PARENT_SPAN_ID, parentSpanId);
    }

    public static String getValueByKey(String key) {
        return MDC.get(key);
    }

    public static void setValueByKey(String key, String value) {
        MDC.put(key, value);
    }

    public static void removeByKey(String key) {
        MDC.remove(key);
    }
}