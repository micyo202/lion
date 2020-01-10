package com.lion.bigdata.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

/**
 * JavaWordCountLambda
 * Spark单词计数WordCount示例（Lambda版）
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/01/07
 * Copyright 2020 Yanzheng. All rights reserved.
 */
public class JavaWordCountLambda {

    public static void main(String[] args) {
        /**
         * 创建SparkConf并设置配置信息
         * local
         * local[2]
         * spark://192.168.1.200:7077
         */
        SparkConf sparkConf = new SparkConf().setAppName("JavaWordCount").setMaster("local[2]");
        // 创建spark程序入口
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        // 指定从哪里读取数据
        JavaRDD<String> lines = sparkContext.textFile("/Users/apple/Desktop/words.txt");
        // 切分压平
        JavaRDD<String> words = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

        // 将单词和1放在一起
        JavaPairRDD<String, Integer> wordAndOne = words.mapToPair(word -> new Tuple2<>(word, 1));

        // 聚合
        JavaPairRDD<String, Integer> reduced = wordAndOne.reduceByKey((a, b) -> a + b);

        // 排序（Java的RDD只支持sortByKey，但是key是单词不是次数）
        // 调换单词和次数的顺序 (hello, 7) -> (7, hello)
        JavaPairRDD<Integer, String> swaped = reduced.mapToPair(tp -> tp.swap());

        // 排序
        JavaPairRDD<Integer, String> sorted = swaped.sortByKey(false);

        // 将顺序还原
        JavaPairRDD<String, Integer> result = sorted.mapToPair(tp -> tp.swap());

        // 保存数据
        result.saveAsTextFile("/Users/apple/Desktop/res-java-lambda");
        // 释放资源
        sparkContext.stop();
    }

}
