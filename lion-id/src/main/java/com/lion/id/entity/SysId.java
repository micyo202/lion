package com.lion.id.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * SysId
 * 自增Id实体类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/28
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Entity
@Table(name = "sys_id")
@Data
public class SysId implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * 编码
     */
    private String code;
    /**
     * id最大值
     */
    @Column(name = "max_id")
    private Integer maxId;
    /**
     * 步长
     */
    private Integer step;
    /**
     * 名称
     */
    @Column(name = "name")
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