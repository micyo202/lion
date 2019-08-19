package com.lion.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * User
 * 用户实体
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/13
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    private Integer id;
    private String avatar;
    private String username;
    private String password;
    private String salt;
    private String name;
    private Date birthday;
    private Integer sex;
    private String email;
    private String phone;
    private Integer status;
    private Date createTime;
    private Date updateTime;

}
