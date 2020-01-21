package com.lion.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
 * SaveRDD
 * 对RDD进行保存操作
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/1/7
 *       Copyright 2020 Yanzheng. All rights reserved.
 */
object SaveRDD {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("SaveData").setMaster("local[2]")
    val sparkContext = new SparkContext(sparkConf)

    val result = sparkContext.textFile("hdfs://192.168.1.200:9000/input").flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)

    // 方法一：forearch 是一个action操作，从结果中一条一条拿数据（如果数据量很大，不推荐）
    /*
    result.foreach(data => {
      // 对每一条数据进行保存（存储到mysql或。。。）
      println(data._1)
    })
    */

    // 方法二：拿出来一个分区，一个分区就是一个迭代器iterator
    result.foreachPartition(iter => {
      println(iter.foreach(data =>
        println(data)
      ))
    })

    sparkContext.stop()
  }
}
