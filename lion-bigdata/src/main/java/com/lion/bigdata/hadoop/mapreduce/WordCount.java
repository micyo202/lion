package com.lion.bigdata.hadoop.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

/**
 * WordCount
 * TODO
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/06/13
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class WordCount {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
        /*
        conf.set("fs.defaultFS", "hdfs://192.168.1.200:9000");//设置hdfs的通讯地址
        conf.set("mapreduce.framework.name", "yarn");
        conf.set("yarn.resoucemanager.hostname", "192.168.1.200");//设置RM的主机
        conf.set("yarn.resourcemanager.address", "192.168.1.200:8032");
        conf.set("mapreduce.app-submission.cross-platform", "true");//意思是跨平台提交，在windows下如果没有这句代码会报错 "/bin/bash: line 0: fg: no job control"，去网上搜答案很多都说是linux和windows环境不同导致的一般都是修改YarnRunner.java，但是其实添加了这行代码就可以了。
        */

        Job job = Job.getInstance(conf, "WordCount");

        job.setJarByClass(WordCount.class);

        job.setMapperClass(WordMap.class);
        job.setReducerClass(WordReduce.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        /**
         * 本地输入路径：/Users/apple/Desktop/input
         * 本地输出路径：/Users/apple/Desktop/output
         *
         * HDFS输入路径：hdfs://192.168.1.200:9000/input
         * HDFS输出路径：hdfs://192.168.1.200:9000/output
         */
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}