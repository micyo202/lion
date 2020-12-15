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
package com.lion.common.logging.stdout;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.Log;

/**
 * StdOutImpl
 * 日志文件输出Sql
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/11/27
 * @see org.apache.ibatis.logging.stdout.StdOutImpl
 */
@Slf4j
public class StdOutImpl implements Log {

    public StdOutImpl(String clazz) {}

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void error(String s, Throwable e) {
        System.err.println(s);
        log.error(s, e);
        e.printStackTrace(System.err);
    }

    @Override
    public void error(String s) {
        System.err.println(s);
        log.error(s);
    }

    @Override
    public void debug(String s) {
        System.out.println(s);
        log.debug(s);
    }

    @Override
    public void trace(String s) {
        System.out.println(s);
        log.trace(s);
    }

    @Override
    public void warn(String s) {
        System.out.println(s);
        log.warn(s);
    }
}