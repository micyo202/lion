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
package com.lion.demo.consumer.alg;

import org.junit.jupiter.api.Test;

/**
 * AlgTests
 * 二分算法
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/03/29
 */
class AlgTests {

    @Test
    void test() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int value = binary(a, 9);
        System.out.println(value);
    }

    /**
     * 二分算法
     *
     * @param array 数组
     * @param value 需要查找的值
     * @return 对应的位置
     */
    private int binary(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (value == array[middle]) {
                return middle;
            }
            if (value > array[middle]) {
                low = middle + 1;
            }
            if (value < array[middle]) {
                high = middle - 1;
            }
        }
        return -1;
    }
}
