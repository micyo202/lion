package com.lion.upms.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * SysMenu
 * 菜单实体类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/10
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Entity
@Table(name = "sys_menu")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysMenu implements Serializable {

    /**
     * 主键
     */
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
     * 菜单名称
     */
    private String name;
    /**
     * 菜单url
     */
    private String url;
    /**
     * 菜单层级
     */
    private Integer level;
    /**
     * 菜单排序
     */
    private Integer sort;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 是否是菜单
     */
    @Column(name = "is_menu")
    private Boolean isMenu;
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
