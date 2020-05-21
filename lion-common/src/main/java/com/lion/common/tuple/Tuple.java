/**
 *   Copyright 2019 Yanzheng (https://github.com/micyo202). All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.lion.common.tuple;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Tuple
 * 自定义元组
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/5/20
 */
@ToString
@AllArgsConstructor
public abstract class Tuple<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> implements Serializable {

    final T1 _1;
    final T2 _2;
    final T3 _3;
    final T4 _4;
    final T5 _5;
    final T6 _6;
    final T7 _7;
    final T8 _8;
    final T9 _9;
    final T10 _10;

    public static <T1, T2> Tuple2 of(T1 _1, T2 _2) {
        return new Tuple2(_1, _2);
    }

    public static <T1, T2, T3> Tuple3 of(T1 _1, T2 _2, T3 _3) {
        return new Tuple3(_1, _2, _3);
    }

    public static <T1, T2, T3, T4> Tuple4 of(T1 _1, T2 _2, T3 _3, T4 _4) {
        return new Tuple4(_1, _2, _3, _4);
    }

    public static <T1, T2, T3, T4, T5> Tuple5 of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5) {
        return new Tuple5(_1, _2, _3, _4, _5);
    }

    public static <T1, T2, T3, T4, T5, T6> Tuple6 of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6) {
        return new Tuple6(_1, _2, _3, _4, _5, _6);
    }

    public static <T1, T2, T3, T4, T5, T6, T7> Tuple7 of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7) {
        return new Tuple7(_1, _2, _3, _4, _5, _6, _7);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8 of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8) {
        return new Tuple8(_1, _2, _3, _4, _5, _6, _7, _8);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Tuple9 of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9) {
        return new Tuple9(_1, _2, _3, _4, _5, _6, _7, _8, _9);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Tuple10 of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10) {
        return new Tuple10(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10);
    }
}
