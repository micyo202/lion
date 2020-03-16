package com.lion.common.tuple;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Tuple3
 * 自定义元组
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/01/15
 * Copyright 2020 Yanzheng. All rights reserved.
 */
@ToString
@AllArgsConstructor
public class Tuple3<T1, T2, T3> implements Serializable {

    final T1 _1;
    final T2 _2;
    final T3 _3;

    public static <T1, T2, T3> Tuple3 apply(T1 _1, T2 _2, T3 _3) {
        return new Tuple3(_1, _2, _3);
    }

}
