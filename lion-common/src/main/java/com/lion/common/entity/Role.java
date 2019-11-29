package com.lion.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Role
 * 角色实体类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/13
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role implements Serializable {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 角色名称
     */
    private String name;
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
