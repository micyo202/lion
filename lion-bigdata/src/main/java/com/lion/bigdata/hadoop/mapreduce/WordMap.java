package com.lion.bigdata.hadoop.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * WordMap
 * TODO
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/06/13
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class WordMap extends Mapper<LongWritable, Text, Text, IntWritable> {

    private static final IntWritable ONE = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String lineStr = value.toString();
        String[] words = lineStr.split(" ");
        for (String word : words) {
            context.write(new Text(word), ONE);
        }
    }
}
