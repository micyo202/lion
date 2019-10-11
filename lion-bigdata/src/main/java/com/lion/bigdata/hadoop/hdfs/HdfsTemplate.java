package com.lion.bigdata.hadoop.hdfs;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.fs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HdfsTemplate
 * TODO
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/06/12
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Component
@Slf4j
public class HdfsTemplate {

    @Autowired
    FileSystem fileSystem;

    /**
     * 创建文件夹
     * 命令：hdfs dfs -mkdir /test
     */
    public boolean mkdir(String path) {
        boolean res = false;
        Path hdfsPath = new Path(path);
        try {
            if (!fileSystem.exists(hdfsPath)) {
                res = fileSystem.mkdirs(hdfsPath);
            } else {
                log.warn("创建HDFS目录失败，路径已存在，path:{0}", path);
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("创建HDFS目录失败，path:{0}", path);
        }

        return res;

    }

    /**
     * 删除
     * 命令：hdfs dfs -rmr /test
     */
    public boolean delete(String path) {

        if (StringUtils.isBlank(path))
            return false;

        boolean res = false;
        Path hdfsPath = new Path(path);
        try {
            res = fileSystem.delete(hdfsPath, true);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("删除HDFS文件或目录失败，path:{0}", path);
        }

        return res;

    }

    /**
     * 重命名
     * 命令：hdfs dfs -mv /oldTest /newTest
     */
    public boolean rename(String oldPath, String newPath) {

        boolean res = false;

        Path oldHdfsPath = new Path(oldPath);
        Path newHdfsPath = new Path(newPath);

        try {
            res = fileSystem.rename(oldHdfsPath, newHdfsPath);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("重命名失败，oldPath:{0}, newPath:{1}", oldPath, newPath);
        }

        return res;

    }

    /**
     * 上传文件至HDFS
     * 命令：hdfs dfs -put /User/Desktop/word.txt /test
     */
    public void uploadFile(String srcPath, String dstPath) {

        Path srcHdfsPath = new Path(srcPath);
        Path dstHdfsPath = new Path(dstPath);

        try {
            fileSystem.copyFromLocalFile(false, true, srcHdfsPath, dstHdfsPath);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("上传文件至HDFS失败，srcPath:{0}, dstPath:{1}", srcPath, dstPath);
        }

    }

    /**
     * 从HDFS下载文件至本地
     * 命令：hdfs dfs -get /test/test.txt /User/Desktop
     */
    public void downloadFile(String srcPath, String dstPath) {

        Path srcHdfsPath = new Path(srcPath);
        Path dstHdfsPath = new Path(dstPath);

        try {
            fileSystem.copyToLocalFile(srcHdfsPath, dstHdfsPath);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("从HDFS下载文件至本地失败，srcPath:{0}, dstPath:{1}", srcPath, dstPath);
        }

    }

    /**
     * 获取HDFS上面的某个路径下面的所有文件或目录（不包含子目录）信息
     * 命令：hdfs dfs -ls /test
     */
    public List<Map<String, Object>> listFiles(String path, PathFilter pathFilter) {

        //返回数据
        List<Map<String, Object>> result = new ArrayList<>();

        Path hdfsPath = new Path(path);

        try {
            if (fileSystem.exists(hdfsPath)) {
                FileStatus[] statuses;
                //根据Path过滤器查询
                if (pathFilter != null) {
                    statuses = fileSystem.listStatus(hdfsPath, pathFilter);
                } else {
                    statuses = fileSystem.listStatus(hdfsPath);
                }

                if (statuses != null) {
                    for (FileStatus status : statuses) {
                        //每个文件的属性
                        Map<String, Object> fileMap = new HashMap<>(2, 1);

                        fileMap.put("path", status.getPath().toString());
                        fileMap.put("isDir", status.isDirectory());

                        result.add(fileMap);
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            log.error("获取HDFS上面的某个路径下面的所有文件失败，path:{0}", path);
        }

        return result;

    }

    /**
     * 打开HDFS上面的文件并返回 InputStream
     * 命令：hdfs dfs -cat /test/word.txt
     */
    public FSDataInputStream openWithInputStream(String path) {
        //HDFS文件路径
        Path hdfsPath = new Path(path);

        try {
            return fileSystem.open(hdfsPath);
        } catch (IOException e) {
            log.error("打开HDFS上面的文件失败，path:{0}", path);
        }

        return null;
    }

    /**
     * 打开HDFS上面的文件并返回byte数组，方便Web端下载文件
     * 命令：hdfs dfs -cat /test/word.txt
     */
    public byte[] openWithBytes(String path) {
        //HDFS文件路径
        Path hdfsPath = new Path(path);

        FSDataInputStream inputStream = null;

        try {
            inputStream = fileSystem.open(hdfsPath);
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            log.error("打开HDFS上面的文件失败，path:{0}", path);
        }

        return null;
    }

    /**
     * 打开HDFS上面的文件并返回String字符串
     * 命令：hdfs dfs -cat /test/word.txt
     */
    public String openWithString(String path) {
        //HDFS文件路径
        Path hdfsPath = new Path(path);

        FSDataInputStream inputStream = null;

        try {
            inputStream = fileSystem.open(hdfsPath);
            return IOUtils.toString(inputStream, Charset.forName("UTF-8"));
        } catch (IOException e) {
            log.error("打开HDFS上面的文件失败，path:{0}", path);
        }

        return null;
    }

    public <T extends Object> T openWithObject(String path, Class<T> clazz) {
        //1、获得文件的json字符串
        String jsonStr = this.openWithString(path);

        //2、方式一：使用com.alibaba.fastjson.JSON将json字符串转化为Java对象并返回
        //return JSON.parseObject(jsonStr, clazz);

        //2、方式二：使用 jackson 转换对象
        try {
            return new ObjectMapper().readValue(jsonStr, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("打开HDFS上面的文件失败，path:{0}", path);
        }

        return null;
    }

}
