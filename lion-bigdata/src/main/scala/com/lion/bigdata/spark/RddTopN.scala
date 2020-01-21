package com.lion.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * RddTopN
 * 按颜值排序、颜值相等按照年龄排序，获取前几个
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/1/7
 *       Copyright 2020 Yanzheng. All rights reserved.
 */
object RddTopN {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("RddTopN").setMaster("local[*]")

    val sparkContext = new SparkContext(sparkConf)

    val lines = sparkContext.parallelize(List("2,张三,20,99", "3,李四,20,100", "4,王五,21,100", "1,刘六,18,9999"))

    val userRDD: RDD[(Long, String, Int, Int)] = lines.map(line => {
      val fields = line.split("[,]")
      val id = fields(0).toLong
      val name = fields(1)
      val age = fields(2).toInt
      val faceValue = fields(3).toInt
      (id, name, age, faceValue)
    })

    val sorted: RDD[(Long, String, Int, Int)] = userRDD.sortBy(u => User(u._3, u._4))

    println(sorted.take(4).toBuffer)

    sparkContext.stop()
  }

  // 自定义排序规则，集成 Comparable 类重写 compareTo 方法
  case class User(age: Int, faceValue: Int) extends Comparable[User] with Serializable {
    override def compareTo(that: User): Int = {
      if (this.faceValue == that.faceValue) {
        that.age - this.age
      } else {
        that.faceValue - this.faceValue
      }
    }
  }

}
