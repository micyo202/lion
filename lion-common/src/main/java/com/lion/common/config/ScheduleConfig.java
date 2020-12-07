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
package com.lion.common.config;

import com.lion.common.schedule.entity.Schedule;
import com.lion.common.schedule.mapper.ScheduleMapper;
import com.lion.common.util.DateUtil;
import com.lion.common.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * ScheduleConfig
 * 定时任务启动
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/08/09
 */
@Component
@EnableScheduling
@Slf4j
public class ScheduleConfig implements SchedulingConfigurer {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private ScheduleMapper scheduleMapper;

    /**
     * 定时任务执行数
     */
    private int scheduleTaskCount = 0;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        final List<Schedule> scheduleList = scheduleMapper.getScheduleListByAppName(applicationName);

        if (CollectionUtils.isNotEmpty(scheduleList)) {
            log.info("定时任务即将启动，预计启动任务数量[" + scheduleList.size() + "]，时间：" + DateUtil.getCurrentDateTime());
            for (Schedule schedule : scheduleList) {
                // 判断任务是否有效
                if (schedule.getValid()) {
                    // 执行定时任务
                    taskRegistrar.addTriggerTask(getRunnable(schedule), getTrigger(schedule));
                    scheduleTaskCount++;
                }
            }
            log.info("定时任务实际启动数量[" + scheduleTaskCount + "]，时间：" + DateUtil.getCurrentDateTime());
        }
    }

    private Runnable getRunnable(Schedule schedule) {
        return () -> {
            try {
                final Object bean = SpringUtil.getBean(schedule.getClassName());
                final Method method = bean.getClass().getMethod(schedule.getMethod(), (Class<?>[]) null);
                method.invoke(bean);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                log.error("定时任务调度失败", e);
            }
        };
    }

    private Trigger getTrigger(Schedule schedule) {
        return triggerContext -> {
            // 将Cron 0/1 * * * * ? 输入取得下一次执行的时间
            final CronTrigger cronTrigger = new CronTrigger(schedule.getCron());
            final Date date = cronTrigger.nextExecutionTime(triggerContext);
            return date;
        };
    }

}