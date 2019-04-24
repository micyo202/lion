package com.lion.demo.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SampleApplicationTests
 * TODO
 *
 * @author Yanzheng
 * @date 2019/01/06
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() {
        System.out.println("加载配置文件...");
    }

    @Test
    public void testRequest() {
        String url = "http://localhost:" + port;
        ResponseEntity<String> response1 = this.restTemplate.getForEntity(
                url + "/gray", String.class);
        System.out.println(String.format("1测试结果为：%s", response1.getBody()));

        ResponseEntity<String> response2 = this.restTemplate.getForEntity(
                url + "/product/1", String.class);
        System.out.println(String.format("2测试结果为：%s", response2.getBody()));
    }

}
