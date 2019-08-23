package com.lion.common.tuple;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Optional;

/**
 * Tuple3
 * TODO
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/08/14
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@ToString
@AllArgsConstructor
public class Tuple4<T1, T2, T3, T4> {

    private T1 t1;
    private T2 t2;
    private T3 t3;
    private T4 t4;

    public <T1> Optional<T1> _1() {
        return (Optional<T1>) Optional.ofNullable(t1);
    }

    public <T2> Optional<T2> _2() {
        return (Optional<T2>) Optional.ofNullable(t2);
    }

    public <T3> Optional<T3> _3() {
        return (Optional<T3>) Optional.ofNullable(t3);
    }

    public <T4> Optional<T4> _4() {
        return (Optional<T4>) Optional.ofNullable(t4);
    }

    public static <T1, T2, T3, T4> Tuple4 of(T1 t1, T2 t2, T3 t3, T4 t4) {
        return new Tuple4(t1, t2, t3, t4);
    }

}
