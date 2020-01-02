package com.lion.bigdata.hadoop.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * WordReduce
 * TODO
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/06/13
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class WordReduce extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        for (IntWritable num : values) {
            count += num.get();
        }
        context.write(key, new IntWritable(count));
    }
}
