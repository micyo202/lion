package com.lion.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * ScalaWordCount
 * 使用scala实现单词计数WordCount
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/1/7
 *       Copyright 2020 Yanzheng. All rights reserved.
 */
object ScalaWordCount {

  def main(args: Array[String]): Unit = {
    /**
     * 1、创建SparkConf并设置配置信息
     * local
     * local[2]
     * spark://192.168.1.200:7077
     */
    val sparkConf = new SparkConf().setAppName("ScalaWordCount").setMaster("local[2]");
    // 2、SparkContext，他是spark程序执行的入口
    val sparkContext = new SparkContext(sparkConf);
    // 3、编写spark程序
    // 方法一
    // sparkContext.textFile(args(0)).flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).saveAsTextFile(args(1))
    // 方法二（推荐：可读性强）
    // 指定从哪里读取数据并生成RDD（RDD是分布式数据集）
    //val lines: RDD[String] = sparkContext.textFile(args(0))
    val lines: RDD[String] = sparkContext.textFile("/Users/apple/Desktop/words.txt")
    // 将一行内容进行切分压平（RDD转换）
    val words: RDD[String] = lines.flatMap(_.split(" "))
    // 将单词和1放大一个元组里
    val wordAndOne: RDD[(String, Int)] = words.map((_, 1))
    // 聚合操作
    val reduced: RDD[(String, Int)] = wordAndOne.reduceByKey(_ + _)
    // 排序操作
    val sorted: RDD[(String, Int)] = reduced.sortBy(_._2, false)
    // 保存数据到指定位置
    //sorted.saveAsTextFile(args(1))
    sorted.saveAsTextFile("/Users/apple/Desktop/res-scala")
    // 4、释放资源
    sparkContext.stop()
  }

}
