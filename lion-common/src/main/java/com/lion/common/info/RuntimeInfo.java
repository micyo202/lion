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
package com.lion.common.info;

import java.io.Serializable;

/**
 * RuntimeInfo
 * 运行时信息，包括内存总大小、已用大小、可用大小等
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/10/16
 */
public class RuntimeInfo implements Serializable {

    private Runtime currentRuntime = Runtime.getRuntime();

    /**
     * 获得运行时对象
     */
    public final Runtime getRuntime() {
        return currentRuntime;
    }

    /**
     * 获得JVM最大可用内存
     */
    public final long getMaxMemory() {
        return currentRuntime.maxMemory();
    }

    /**
     * 获得JVM已分配内存
     */
    public final long getTotalMemory() {
        return currentRuntime.totalMemory();
    }

    /**
     * 获得JVM已分配内存中的剩余空间
     */
    public final long getFreeMemory() {
        return currentRuntime.freeMemory();
    }

    /**
     * 获得JVM最大可用内存
     */
    public final long getUsableMemory() {
        return currentRuntime.maxMemory() - currentRuntime.totalMemory() + currentRuntime.freeMemory();
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("Runtime:         ").append(getRuntime())
                .append("\nMax Memory:      ").append(getMaxMemory())
                .append("\nTotal Memory:    ").append(getTotalMemory())
                .append("\nFree Memory:     ").append(getFreeMemory())
                .append("\nUsable Memory:   ").append(getUsableMemory());

        return builder.toString();
    }

}