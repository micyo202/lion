package com.lion.common.constant;

/**
 * LogConstant
 * 日志常量
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/6/10
 */
public interface LogConstant {

    /**
     * 调用链
     */
    String TRACE_ID = "X-B3-TraceId";

    /**
     * 调用单元
     */
    String SPAN_ID = "X-B3-SpanId";

    /**
     * 上级调用单元
     */
    String PARENT_SPAN_ID = "X-B3-ParentSpanId";
}
