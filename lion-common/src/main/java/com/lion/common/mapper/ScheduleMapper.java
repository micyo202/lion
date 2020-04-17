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
package com.lion.common.mapper;

import com.lion.common.entity.Schedule;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ScheduleMapper
 * 定时任务Mapper方法
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/08/09
 */
public interface ScheduleMapper {

    @Select("select id, name, cron, app_name appName, class_name className, method, valid from sys_schedule where valid=1 and app_name=#{appName} order by id")
    List<Schedule> getScheduleListByAppName(String appName);
}