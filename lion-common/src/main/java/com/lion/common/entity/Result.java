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
package com.lion.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lion.common.constant.ResponseCode;
import com.lion.common.constant.ResponseStatus;
import com.lion.common.util.DateUtil;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Result
 * 结果实体类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/04/13
 */
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {

    /**
     * 状态值
     */
    private int code = ResponseCode.SUCCESS;

    /**
     * 提示信息
     */
    private String msg = "Success";

    /**
     * 数据
     */
    private T data;

    /**
     * 时间戳
     */
    private long timestamp = DateUtil.getTimestamp();

    /**
     * 额外扩展数据
     */
    //@JsonIgnore
    private Map<String, Object> extra = null;

    /**
     * 自定义相关getter、setter方法
     */
    public int getCode() {
        return code;
    }

    public Result<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Result<T> setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public Result<T> setExend(Map<String, Object> extra) {
        this.extra = extra;
        return this;
    }

    /**
     * 自定义扩展方法
     */
    public Result<T> addExtra(String key, Object value) {
        if (null == this.extra) {
            this.extra = new HashMap<>(8);
        }
        this.extra.put(key, value);
        return this;
    }

    public Result<T> setStatus(ResponseStatus responseStatus) {
        this.setCode(responseStatus.code());
        this.setMsg(responseStatus.msg());
        return this;
    }

    public static Result status(ResponseStatus responseStatus) {
        Result result = new Result();
        result.setCode(responseStatus.code());
        result.setMsg(responseStatus.msg());
        return result;
    }

    public static Result success() {
        return new Result();
    }

    public static <T> Result success(T data) {
        Result result = new Result();
        result.setData(data);
        return result;
    }

    public static <T> Result success(T data, Map<String, Object> extra) {
        Result result = new Result();
        result.setData(data);
        result.setExend(extra);
        return result;
    }

    public static Result failure(String msg) {
        Result result = new Result();
        result.setCode(ResponseCode.FAILURE);
        result.setMsg(msg);
        return result;
    }

    public static Result failure(int code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}