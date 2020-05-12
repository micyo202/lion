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

import org.junit.jupiter.api.Test;

/**
 * TupleTests
 * TODO
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/4/3
 */
public class TupleTests {

    @Test
    public void testTuple() {
        Tuple2 tuple2 = Tuple2.apply(1, 2);
        Tuple3 tuple3 = Tuple3.apply(1, 2, 3);
        Tuple4 tuple4 = Tuple4.apply(1, 2, 3, 4);
        Tuple5 tuple5 = Tuple5.apply(1, 2, 3, 4, 5);
        Tuple6 tuple6 = Tuple6.apply(1, 2, 3, 4, 5, 6);
        Tuple7 tuple7 = Tuple7.apply(1, 2, 3, 4, 5, 6, 7);
        Tuple8 tuple8 = Tuple8.apply(1, 2, 3, 4, 5, 6, 7, 8);
        Tuple9 tuple9 = Tuple9.apply(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Tuple10 tuple10 = Tuple10.apply(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println(tuple2);
        System.out.println(tuple3);
        System.out.println(tuple4);
        System.out.println(tuple5);
        System.out.println(tuple6);
        System.out.println(tuple7);
        System.out.println(tuple8);
        System.out.println(tuple9);
        System.out.println(tuple10);
    }

}
