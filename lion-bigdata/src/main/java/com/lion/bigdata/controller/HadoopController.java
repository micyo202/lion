package com.lion.bigdata.controller;

import com.lion.bigdata.hadoop.hdfs.HdfsTemplate;
import org.apache.hadoop.fs.FSDataInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * HadoopController
 * Hadoop示例
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/06/13
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@RestController
public class HadoopController {

    @Autowired
    private HdfsTemplate hdfsTemplate;

    @RequestMapping("/hdfs/{type}")
    public String hdfs(@PathVariable String type) {

        String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String filePath = "/file_" + dateStr;
        String msg;

        switch (type) {
            case "mkdir": {
                boolean mkdir = hdfsTemplate.mkdir(filePath);
                msg = "mkdir " + (mkdir ? "SUCCESS" : "FAILURE");
                break;
            }
            case "rename": {
                boolean rename = hdfsTemplate.rename(filePath, filePath + "_rename");
                msg = "rename " + (rename ? "SUCCESS" : "FAILURE");
                break;
            }
            case "delete": {
                boolean delete = hdfsTemplate.delete(filePath);
                msg = "delete " + (delete ? "SUCCESS" : "FAILURE");
                break;
            }
            case "upload": {
                hdfsTemplate.uploadFile("/Users/apple/Desktop/schema.sql", filePath);
                msg = "uploadFile SUCCESS";
                break;
            }
            case "download": {
                hdfsTemplate.downloadFile("/input/word.txt", "/Users/apple/Desktop");
                msg = "downloadFile SUCCESS";
                break;
            }
            case "list": {
                StringBuilder sb = new StringBuilder("list:\n");
                List<Map<String, Object>> list = hdfsTemplate.listFiles("/output", null);
                list.stream().forEach(map -> map.forEach((k, v) -> {
                    sb.append("Key = " + k + "，Value = " + v + "\n");
                }));
                msg = sb.toString();
                break;
            }
            case "open-stream": {
                FSDataInputStream fsDataInputStream = hdfsTemplate.openWithInputStream("/output/part-r-00000");
                msg = "openWithInputStream:\n" + fsDataInputStream.toString();
                break;
            }
            case "open-bytes": {
                byte[] bytes = hdfsTemplate.openWithBytes("/output/part-r-00000");
                msg = "openWithBytes:\n" + bytes.toString();
                break;
            }
            case "open-string": {
                String str = hdfsTemplate.openWithString("/output/part-r-00000");
                msg = "openWithString:\n" + str;
                break;
            }
            case "open-object": {
                Map obj = hdfsTemplate.openWithObject("/output/part-r-00000", Map.class);
                msg = "openWithObject:\n" + obj;
                break;
            }
            default: {
                msg = "参数类型不存在，type:" + type;
                break;
            }
        }

        return msg;
    }

}
