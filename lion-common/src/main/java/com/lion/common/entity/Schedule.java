package com.lion.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

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