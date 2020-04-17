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
package com.lion.common.exception;

import com.lion.common.constant.ResponseCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * LionException
 * 自定义异常类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/07/17
 */
@Getter
@Setter
@NoArgsConstructor
public class LionException extends RuntimeException {

    /**
     * 错误码
     */
    private int code;

    public LionException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public LionException(String msg) {
        super(msg);
        this.code = ResponseCode.FAILURE;
    }

}
