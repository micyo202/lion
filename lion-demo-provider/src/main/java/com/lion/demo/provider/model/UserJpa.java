package com.lion.demo.provider.model;

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
 * UserJpa
 * 使用 Spring Data Jpa 方式
 *
 * @author Yanzheng
 * @date 2019/03/29
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Entity
@Data
@Table(name = "user_jpa")
@NoArgsConstructor
@AllArgsConstructor
public class UserJpa implements Serializable {

    @Id
    @Column(nullable = false, length = 36)
    private String id;

    @Column(nullable = false, length = 36)
    private String username;

    @Column(nullable = false, length = 36)
    private String password;

    @Column
    private Date birthday;

    @Column
    private int type;

    @Column
    private boolean status;

    @Column
    private Date jointime;

}
