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
package consumer;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

/**
 * ConsumerDemoApplicationTests
 * TODO
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/11/26
 * Copyright 2019 Yanzheng. All rights reserved.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ConsumerDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConsumerDemoApplicationTests {

    //@LocalServerPort
    private int port;

    //@Autowired
    private TestRestTemplate restTemplate;

    //@Test
    public void contextLoads() {
        System.out.println("加载配置文件...");
    }

    //@Test
    public void testRequest() {
        String url = "http://localhost:" + port;
        ResponseEntity<String> response = this.restTemplate.getForEntity(
                url + "/init", String.class);
        System.out.println(String.format("/init 调用测试结果为：%s", response.getBody()));
    }

}
