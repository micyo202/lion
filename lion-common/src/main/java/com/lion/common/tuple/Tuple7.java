package com.lion.common.tuple;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Tuple7
 * 自定义元组
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/01/15
 * Copyright 2020 Yanzheng. All rights reserved.
 */
@ToString
@AllArgsConstructor
public class Tuple7<T1, T2, T3, T4, T5, T6, T7> implements Serializable {

    final T1 _1;
    final T2 _2;
    final T3 _3;
    final T4 _4;
    final T5 _5;
    final T6 _6;
    final T7 _7;

    public static <T1, T2, T3, T4, T5, T6, T7> Tuple7 apply(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7) {
        return new Tuple7(_1, _2, _3, _4, _5, _6, _7);
    }

}
