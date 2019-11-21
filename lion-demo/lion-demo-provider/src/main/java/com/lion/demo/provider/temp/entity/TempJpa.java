package com.lion.demo.provider.temp.entity;

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
 * @date 2019/04/15
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Entity
@Table(name = "temp_jpa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempJpa implements Serializable {

    @Id
    @Column(nullable = false, length = 32)
    private String id;
    @Column(length = 32)
    private String name;
    private Integer type;
    private Boolean status;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

}
