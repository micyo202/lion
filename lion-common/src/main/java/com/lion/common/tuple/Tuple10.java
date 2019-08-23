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
public class Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> {

    private T1 t1;
    private T2 t2;
    private T3 t3;
    private T4 t4;
    private T5 t5;
    private T6 t6;
    private T7 t7;
    private T8 t8;
    private T9 t9;
    private T10 t10;

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

    public <T5> Optional<T5> _5() {
        return (Optional<T5>) Optional.ofNullable(t5);
    }

    public <T6> Optional<T6> _6() {
        return (Optional<T6>) Optional.ofNullable(t6);
    }

    public <T7> Optional<T7> _7() {
        return (Optional<T7>) Optional.ofNullable(t7);
    }

    public <T8> Optional<T8> _8() {
        return (Optional<T8>) Optional.ofNullable(t8);
    }

    public <T9> Optional<T9> _9() {
        return (Optional<T9>) Optional.ofNullable(t9);
    }

    public <T10> Optional<T10> _10() {
        return (Optional<T10>) Optional.ofNullable(t10);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Tuple10 of(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10) {
        return new Tuple10(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10);
    }

}
