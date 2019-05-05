package com.lion.id.entity;

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
 * SysId
 * TODO
 *
 * @author Yanzheng
 * @date 2019/04/28
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Entity
@Table(name = "sys_id")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysId implements Serializable {

    @Id
    private Integer id;
    @Column(name = "biz_tag")
    private String bizTag;
    @Column(name = "max_id")
    private Integer maxId;
    private Integer step;
    @Column(name = "[describe]")
    private String describe;
    @Column(name = "update_time")
    private Date updateTime;

}