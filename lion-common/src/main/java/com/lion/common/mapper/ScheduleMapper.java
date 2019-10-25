package com.lion.common.mapper;

import com.lion.common.entity.Schedule;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ScheduleMapper
 * 定时任务Mapper方法
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/08/09
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Service("scheduleMapper")
public interface ScheduleMapper {

    @Select("select id, name, cron, app_name appName, class_name className, method, valid from sys_schedule where valid='1' and app_name=#{appName} order by id")
    List<Schedule> getScheduleListByAppName(String appName);

}
