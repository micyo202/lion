package com.lion.common.tuple;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Optional;

/**
 * Tuple2
 * 自定义元组
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/08/14
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@ToString
@AllArgsConstructor
public class Tuple2<T1, T2> {

    private T1 t1;
    private T2 t2;

    public <T1> Optional<T1> _1() {
        return (Optional<T1>) Optional.ofNullable(t1);
    }

    public <T2> Optional<T2> _2() {
        return (Optional<T2>) Optional.ofNullable(t2);
    }

    public static <T1, T2> Tuple2 of(T1 t1, T2 t2) {
        return new Tuple2(t1, t2);
    }

}