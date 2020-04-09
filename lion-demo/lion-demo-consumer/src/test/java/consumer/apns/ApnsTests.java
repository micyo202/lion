package consumer.apns;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

/**
 * ApnsTests
 * 苹果APNS推送
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/03/29
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class ApnsTests {

    public void test() {
        ApnsService service =
                APNS.newService()
                        .withCert("~/apns_development.p12", "666666") // 指定p12文件及密钥
                        .withSandboxDestination() // 使用苹果推送测试服务器
                        //.withProductionDestination() // 使用苹果推送生产服务器
                        .build();

        String payload = APNS.newPayload()
                .alertTitle("推送标题") // 标题
                .alertBody("这是消息推送内容") // 内容
                .customField("sourceCode", "01") // 自定义字段 
                .customField("url", "https://www.qq.com") // 自定义字段
                .sound("default") // 提示声音
                //.sound("msgsound.caf") // 提示声音（自定义）
                .badge(1) // 应用角标
                .build();

        String token = "115fdc9c21897a5b58b294273c36d1aed3f4ce45ba684ffd083891bda0bd3618";

        service.push(token, payload);
    }

}
