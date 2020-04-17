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
package com.lion.demo.consumer.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * ScheduleDemo
 * 定时任务示例，在对应 sys_schedule 表中配置
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/09/24
 */
@Component
@Slf4j
public class ScheduleDemo {

    public void firstMethod() {
        log.info("定时任务111111");
    }

    public void secondMethod() {
        log.info("定时任务222222");
    }

    public void thirdMethod() {
        log.info("定时任务333333");
    }

}
