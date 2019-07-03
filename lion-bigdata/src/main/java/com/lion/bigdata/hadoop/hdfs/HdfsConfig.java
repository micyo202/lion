package com.lion.bigdata.hadoop.hdfs;

import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;

/**
 * HdfsConfig
 * TODO
 *
 * @author Yanzheng
 * @date 2019/06/12
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Configuration
public class HdfsConfig {

    @Value("${hadoop.node-url}")
    String nodeUrl;

    @Bean("fileSystem")
    public FileSystem fileSystem() {

        org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
        FileSystem fileSystem = null;

        try {
            fileSystem = FileSystem.get(URI.create(nodeUrl), conf);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileSystem;
    }

}