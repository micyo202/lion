package com.lion.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Role
 * 角色实体
 *
 * @author Yanzheng
 * @date 2019/04/13
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role implements Serializable {

    private Integer id;
    private String name;
    private String value;
    private String tips;
    private Date createTime;
    private Date updateTime;
    private Integer status;

}
