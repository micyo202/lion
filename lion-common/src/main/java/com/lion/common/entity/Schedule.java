package com.lion.common.entity;

import lombok.Data;

/**
 * Schedule
 * 定时任务实体类
 *
 * @author Yanzheng
 * @date 2019/08/09
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Data
public class Schedule {

    private Integer id;
    private String name;
    private String cron;
    private String appName;
    private String className;
    private String method;
    private String valid;

}