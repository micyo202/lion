package com.lion.demo.sample.apns;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

/**
 * JunitAPNS
 * 苹果APNS推送
 *
 * @author Yanzheng
 * @date 2019/03/29
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class JunitAPNS {

    public void test() {
        ApnsService service =
                APNS.newService()
                        .withCert("/Users/apple/Desktop/Certificates/APNS/apns.p12", "123456") // 指定p12文件及密钥
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

        String token = "34ce6d121ada470bbede6c33501d428019c05997372bb3a07cb4e51ae1ed026c";

        service.push(token, payload);
    }

}
