package com.lion.bigdata.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * JavaWordCount
 * Spark单词计数WordCount示例（普通版）
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/01/07
 * Copyright 2020 Yanzheng. All rights reserved.
 */
public class JavaWordCount {

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
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String line) throws Exception {
                String[] splited = line.split(" ");
                List<String> list = Arrays.asList(splited);
                Iterator<String> iterator = list.iterator();
                return iterator;
            }
        });

        // 将单词和1放在一起
        JavaPairRDD<String, Integer> wordAndOne = words.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String word) throws Exception {
                Tuple2<String, Integer> tuple2 = new Tuple2<>(word, 1);
                return tuple2;
            }
        });

        // 聚合
        JavaPairRDD<String, Integer> reduced = wordAndOne.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer a, Integer b) throws Exception {
                return a + b;
            }
        });

        // 排序（Java的RDD只支持sortByKey，但是key是单词不是次数）
        // 调换单词和次数的顺序 (hello, 7) -> (7, hello)
        JavaPairRDD<Integer, String> swaped = reduced.mapToPair(new PairFunction<Tuple2<String, Integer>, Integer, String>() {
            @Override
            public Tuple2<Integer, String> call(Tuple2<String, Integer> tp) throws Exception {
                //return new Tuple2<>(tp._2(), tp._1());
                return tp.swap();
            }
        });

        // 排序
        JavaPairRDD<Integer, String> sorted = swaped.sortByKey(false);

        // 将顺序还原
        JavaPairRDD<String, Integer> result = sorted.mapToPair(new PairFunction<Tuple2<Integer, String>, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(Tuple2<Integer, String> tp) throws Exception {
                return tp.swap();
            }
        });

        // 保存数据
        result.saveAsTextFile("/Users/apple/Desktop/res-java");

        // 释放资源
        sparkContext.stop();

    }

}
