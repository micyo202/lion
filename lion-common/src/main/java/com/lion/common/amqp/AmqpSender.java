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

import com.lion.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Repository;

/**
 * MessageSender
 * 消息发送器
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/3/31
 * Copyright 2020 Yanzheng. All rights reserved.
 */
@Repository
public class AmqpSender {

    @Autowired
    private AmqpChannel amqpChannel;

    /**
     * 发送消息
     */
    public void send(Result result) {
        amqpChannel.lionOutput().send(MessageBuilder.withPayload(result).build());
    }

}
