package com.lion.upms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * SysMenu
 * TODO
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/10
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Entity
@Table(name = "sys_menu")
@Data
public class SysMenu implements Serializable {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * 菜单编码
     */
    private String code;
    /**
     * 父菜单ID
     */
    @Column(name = "p_id")
    private Integer pId;
    /**
     * 菜单父编码
     */
    @Column(name = "p_code")
    private String pCode;
    /**
     * 名称
     */
    private String name;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 是否是菜单
     */
    @Column(name = "is_menu")
    private Boolean isMenu;
    /**
     * 菜单层级
     */
    private Integer level;
    /**
     * 菜单排序
     */
    private Integer sort;
    private Integer status;
    private String icon;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

}
