package com.lion.upms.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * SysUser
 * 用户实体类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/10
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Entity
@Table(name = "sys_user")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUser implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
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
    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;
    /**
     * 有效性: true 凭证有效, false 凭证无效
     */
    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;
    /**
     * 锁定性: true 未锁定, false 已锁定
     */
    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;
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
