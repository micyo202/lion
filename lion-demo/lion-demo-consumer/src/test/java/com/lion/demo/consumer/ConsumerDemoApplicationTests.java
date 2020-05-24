/**
 *   Copyright 2019 Yanzheng (https://github.com/micyo202). All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.lion.demo.consumer;

import com.lion.common.result.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * ConsumerDemoApplicationTests
 * 单元测试，使用 mock 需将 druid 配置中 web-stat-filter.enabled 设为关闭
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/5/19
 */
//@SpringBootTest(classes = ConsumerDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
public class ConsumerDemoApplicationTests {

    @LocalServerPort
    private int port;

    private String getUrl() {
        return "http://localhost:" + port;
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void restTestInit() {
        String url = getUrl();
        ResponseEntity<Result> response = this.restTemplate.getForEntity(
                url + "/init", Result.class);
        System.out.println(response.getBody());
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void mockTestInit() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/init")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(mvcResult);
    }

    @Test
    public void mockTestFeignHi() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/feign/hi")
                        .param("name", "lion")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(mvcResult);
    }

    @Test
    public void mockTestRibbonHi() throws Exception {
        MvcResult mvcResultGet = mockMvc.perform(
                MockMvcRequestBuilders.get("/ribbon/hi")
                        .param("name", "lion")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(mvcResultGet);

        MvcResult mvcResultPost = mockMvc.perform(
                MockMvcRequestBuilders.post("/ribbon/hi")
                        .param("name", "lion")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(mvcResultPost);
    }

    @Test
    public void mockTestSentinelBlock() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/sentinel/block")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(mvcResult);
    }

    @Test
    public void mockTestSentinelFallback() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/sentinel/fallback")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(mvcResult);
    }

    @Test
    public void mockTestLock() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/lock")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(mvcResult);
    }

    @Test
    public void mockTestAsync() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/async")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(mvcResult);
    }

    @Test
    public void mockTestAMQP() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/amqp")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(mvcResult);
    }

    @Test
    public void mockTestBlockChainMined() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/blockchain/mined")
                        .param("data", "lion")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(mvcResult);
    }

    @Test
    public void mockTestBlockChainDecrypt() throws Exception {
        String blockHash = "a0395b258720152e33155253297b182e";
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/blockchain/decrypt/" + blockHash)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(mvcResult);
    }

    @Test
    public void mockTestTempMybatisSave() throws Exception {
        String num = "3";
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/temp/mybatis/save/" + num)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(mvcResult);
    }

    @Test
    public void mockTestTempMybatisCustomSql() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/temp/mybatis/custom/sql")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(mvcResult);
    }

    @Test
    public void mockTestTempMybatisPage() throws Exception {
        String version = "v1";
        String pageNum = "1";
        String pageSize = "3";
        String url = String.format("/temp/mybatis/page/%s/%s/%s", version, pageNum, pageSize);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get(url)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(mvcResult);
    }

    @Test
    public void mockTestTempOrderCommit() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/temp/order/commit")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(mvcResult);
    }

    @Test
    public void mockTestTempOrderRollback() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/temp/order/rollback")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(mvcResult);
    }

    @Test
    public void mockTestTempOrderException() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/temp/order/exception")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(mvcResult);
    }

}
