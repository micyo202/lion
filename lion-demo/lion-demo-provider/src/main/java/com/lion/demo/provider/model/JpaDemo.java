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
 * JpaDemo
 * 使用 Spring Data Jpa 方式
 *
 * @author Yanzheng 严正
 * @date 2019/01/08
 * Copyright 2019 Yanzheng. All rights reserved.
 */

@Entity
@Data
@Table(name = "demo")
@NoArgsConstructor
@AllArgsConstructor
public class JpaDemo implements Serializable {

    @Id
    @Column(nullable = false, length = 32)
    private String id;

    @Column(nullable = false, length = 32)
    private String username;

    @Column(nullable = false, length = 32)
    private String password;

    @Column(nullable = false, length = 32)
    private String name;

    @Column
    private boolean sex;

    @Column
    private int age;

    @Column
    private Date jointime;

    @Column(length = 32)
    private String type;

    @Column
    private boolean valid;
}
