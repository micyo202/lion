package com.lion.upms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * SysRole
 * TODO
 *
 * @author Yanzheng
 * @date 2019/04/10
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Entity
@Table(name = "sys_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRole implements Serializable {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private String name;
    private String value;
    private String tips;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    private Integer status;

}
