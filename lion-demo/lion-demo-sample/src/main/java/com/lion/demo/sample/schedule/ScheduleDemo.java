package com.lion.demo.sample.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * ScheduleDemo
 * 定时任务示例，在对应 sys_schedule 表中配置
 *
 * @author Yanzheng
 * @date 2019/08/12
 * Copyright 2019 Yanzheng. All rights reserved.
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
