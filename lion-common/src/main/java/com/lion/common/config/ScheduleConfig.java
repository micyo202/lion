package com.lion.common.config;

import com.lion.common.entity.Schedule;
import com.lion.common.mapper.ScheduleMapper;
import com.lion.common.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * ScheduleConfig
 * 定时任务启动类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/08/09
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Component
@Slf4j
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private ScheduleMapper scheduleMapper;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    /**
     * 定时任务执行数
     */
    private int scheduleTaskCount = 0;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        final List<Schedule> scheduleList = scheduleMapper.getScheduleList(applicationName);

        if (null != scheduleList && !scheduleList.isEmpty()) {
            log.info("定时任务即将启动，预计启动任务数量[" + scheduleList.size() + "]，时间：" + LocalDateTime.now().format(dateTimeFormatter));
            for (Schedule schedule : scheduleList) {
                // 判断任务是否有效
                if ("1".equals(schedule.getValid())) {
                    // 执行定时任务
                    taskRegistrar.addTriggerTask(getRunnable(schedule), getTrigger(schedule));
                    scheduleTaskCount++;
                }
            }
            log.info("定时任务实际启动数量[" + scheduleTaskCount + "]，时间：" + LocalDateTime.now().format(dateTimeFormatter));
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
