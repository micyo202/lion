package com.lion.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * User
 * 用户实体类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/13
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码（密文）
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 性别: true 男, false 女
     */
    private Boolean sex;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 可用性: true 可用, false 不可用
     */
    private Boolean enabled;
    /**
     * 过期性: true 没过期, false 过期
     */
    private Boolean accountNonExpired;
    /**
     * 有效性: true 凭证有效, false 凭证无效
     */
    private Boolean credentialsNonExpired;
    /**
     * 锁定性: true 未锁定, false 已锁定
     */
    private Boolean accountNonLocked;
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
