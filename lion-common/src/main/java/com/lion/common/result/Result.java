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
package com.lion.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lion.common.constant.ResponseCode;
import com.lion.common.constant.ResponseStatus;
import com.lion.common.tuple.Tuple2;
import com.lion.common.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.MapUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Result
 * 结果实体
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/04/13
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "结果实体类")
public class Result<T> implements Serializable {

    /**
     * 状态值
     */
    @ApiModelProperty(value = "状态值")
    private int code;

    /**
     * 提示信息
     */
    @ApiModelProperty(value = "提示信息")
    private String msg;

    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private long timestamp;

    /**
     * 数据
     */
    @ApiModelProperty(value = "数据")
    private T data;

    /**
     * 额外扩展数据
     */
    @ApiModelProperty(value = "额外扩展数据")
    private Map<String, Object> extra = null;

    /**
     * 添加扩展数据
     *
     * @param key   键
     * @param value 值
     */
    public Result<T> addExtra(String key, Object value) {
        if (MapUtils.isEmpty(this.extra)) {
            this.extra = new HashMap<>(8);
        }
        this.extra.put(key, value);
        return this;
    }

    /**
     * 添加扩展数据
     *
     * @param tuple 元组
     */
    public Result<T> addExtra(Tuple2 tuple) {
        if (MapUtils.isEmpty(this.extra)) {
            this.extra = new HashMap<>(8);
        }
        this.extra.put(tuple._1().toString(), tuple._2());
        return this;
    }

    /**
     * 设置状态
     *
     * @param responseStatus 响应状态
     */
    public static <T> Result<T> status(ResponseStatus responseStatus) {
        Result<T> result = new Result<>();
        result
                .setCode(responseStatus.code())
                .setMsg(responseStatus.msg())
                .setTimestamp(DateUtil.getTimestamp());
        return result;
    }

    /**
     * 成功
     */
    public static <T> Result<T> success() {
        return success(null, null);
    }

    /**
     * 成功
     *
     * @param data 数据对象
     */
    public static <T> Result<T> success(T data) {
        return success(data, null);
    }

    /**
     * 成功
     *
     * @param data  数据对象
     * @param extra 扩展数据
     */
    public static <T> Result<T> success(T data, Map<String, Object> extra) {
        Result<T> result = new Result<>();
        result
                .setCode(ResponseCode.SUCCESS)
                .setMsg(ResponseStatus.SUCCESS.msg())
                .setTimestamp(DateUtil.getTimestamp())
                .setData(data)
                .setExtra(extra);
        return result;
    }

    /**
     * 失败
     */
    public static <T> Result<T> failure() {
        return failure(ResponseCode.FAILURE, ResponseStatus.FAILURE.msg());
    }

    /**
     * 失败
     *
     * @param msg 提示信息
     */
    public static <T> Result<T> failure(String msg) {
        return failure(ResponseCode.FAILURE, msg);
    }

    /**
     * 失败
     *
     * @param code 编码
     * @param msg  提示信息
     */
    public static <T> Result<T> failure(int code, String msg) {
        Result<T> result = new Result<>();
        result
                .setCode(code)
                .setMsg(msg)
                .setTimestamp(DateUtil.getTimestamp());
        return result;
    }
}