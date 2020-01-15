package com.lion.common.tuple;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Tuple2
 * 自定义元组
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/01/15
 * Copyright 2020 Yanzheng. All rights reserved.
 */
@ToString
@AllArgsConstructor
public class Tuple2<T1, T2> implements Serializable {

    final T1 _1;
    final T2 _2;

    /*
    public Tuple2(T1 _1, T2 _2) {
        this._1 = Objects.requireNonNull(_1, "_1");
        this._2 = Objects.requireNonNull(_2, "_2");
    }
    */

    public static <T1, T2> Tuple2 apply(T1 _1, T2 _2) {
        return new Tuple2(_1, _2);
    }

}