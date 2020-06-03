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
package com.lion.common.amqp;

import com.lion.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * AmqpReceiver
 * 消息接收器
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/3/31
 */
@EnableBinding(AmqpChannel.class)
@Slf4j
public class AmqpReceiver {

    /**
     * 接收消息
     * @param result 消息对象
     */
    @StreamListener(AmqpChannel.LION_INPUT)
    public void receive(Result result) {
        log.info("AMQP -> 接收到消息：" + result);
    }

}
