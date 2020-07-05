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
package com.lion.common.schedule.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lion.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Schedule
 * 定时任务实体类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/08/09
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "定时任务实体类")
public class Schedule extends BaseEntity<Schedule> {

    private static final long serialVersionUID = 1L;

    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称")
    private String name;

    /**
     * cron表达式
     */
    @ApiModelProperty(value = "cron表达式")
    private String cron;

    /**
     * 执行应用名
     */
    @ApiModelProperty(value = "执行应用名")
    private String appName;

    /**
     * 执行类
     */
    @ApiModelProperty(value = "执行类")
    private String className;

    /**
     * 执行方法
     */
    @ApiModelProperty(value = "执行方法")
    private String method;
}