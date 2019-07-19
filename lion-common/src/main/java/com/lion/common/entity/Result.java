package com.lion.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * Result
 * 响应结果实体
 *
 * @author Yanzheng
 * @date 2019/04/13
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("unchecked")
public class Result<T> implements Serializable {

    private Integer code = 200;
    private String msg = "SUCCESS";
    private String description;
    private T data;
    @JsonIgnore
    private Map<String, Object> extra;

    /**
     * 构造方法
     */
    public Result() {
        extra = new HashMap();
    }

    /**
     * 自定义相关getter、setter方法
     */
    public Integer getCode() {
        return code;
    }

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Result setDescription(String description) {
        this.description = description;
        return this;
    }

    public Map<String, Object> extra() {
        return extra;
    }

    public void setExend(Map<String, Object> extra) {
        this.extra = extra;
    }

    /**
     * 额外扩展方法
     */
    public Result setStatus(Status status) {
        this.code = status.getCode();
        this.msg = status.getMsg();
        return this;
    }

    public static Result status(Status status) {
        Result result = new Result();
        result.setCode(status.getCode());
        result.setMsg(status.getMsg());
        return result;
    }

    public static Result failure(int code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result success() {
        return new Result();
    }

    public static Result success(String msg) {
        Result result = new Result();
        result.put("msg", msg);
        return result;
    }

    public static <T> Result success(T data) {
        Result result = new Result();
        result.setData(data);
        return result;
    }

    public static Result success(Map<String, Object> map) {
        Result result = new Result();
        result.extra.putAll(map);
        return result;
    }

    public Result put(String key, Object value) {
        extra.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", description='" + description + '\'' +
                ", data=" + data +
                ", extra=" + extra +
                '}';
    }
}
