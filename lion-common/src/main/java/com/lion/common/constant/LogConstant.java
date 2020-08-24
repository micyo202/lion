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
