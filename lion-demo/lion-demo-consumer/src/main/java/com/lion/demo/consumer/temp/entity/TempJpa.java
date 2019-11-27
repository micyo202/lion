package com.lion.demo.consumer.temp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * TempJpa
 * 示例Jpa实体类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/11/27
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Entity
@Table(name = "temp_jpa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempJpa implements Serializable {

    /**
     * 主键
     */
    @Id
    @Column(nullable = false, length = 32)
    private String id;
    /**
     * 名称
     */
    @Column(length = 36)
    private String name;
    /**
     * 是否有效: true 有效/未删除, false 无效/已删除
     */
    private Boolean valid;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

}
