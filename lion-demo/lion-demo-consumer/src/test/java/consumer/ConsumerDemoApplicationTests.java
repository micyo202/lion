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
//@SpringBootTest(classes = SampleDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
