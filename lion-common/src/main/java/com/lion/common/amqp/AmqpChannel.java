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

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * MessageChannel
 * 消息通道
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/3/31
 */
public interface AmqpChannel {

    String LION_INPUT = "lion-input";
    String LiON_OUTPUT = "lion-output";

    /**
     * 接收消息
     */
    @Input(LION_INPUT)
    SubscribableChannel lionInput();

    /**
     * 发送消息
     */
    @Output(LiON_OUTPUT)
    MessageChannel lionOutput();
}
