package consumer.alg;


import org.junit.jupiter.api.Test;

/**
 * AlgTests
 * 二分算法
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/03/29
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class AlgTests {

    @Test
    public void test() {
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
