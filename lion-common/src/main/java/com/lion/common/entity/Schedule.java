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
package com.lion.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Schedule
 * 定时任务实体类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/08/09
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Schedule implements Serializable {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * cron表达式
     */
    private String cron;

    /**
     * 执行应用名
     */
    private String appName;

    /**
     * 执行类
     */
    private String className;

    /**
     * 执行方法
     */
    private String method;

    /**
     * 是否有效: true 有效/未删除, false 无效/已删除
     */
    private Boolean valid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}