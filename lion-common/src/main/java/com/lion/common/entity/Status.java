package com.lion.common.entity;

/**
 * Status
 * 响应状态枚举
 *
 * @author Yanzheng
 * @date 2019/04/13
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public enum Status {

    //处理成功
    SUCCESS(200, "SUCCESS"),

    //处理失败
    FAILURE(500, "FAILURE"),

    //未登录
    NOT_LOGIN(401, "Not Login"),

    //未激活
    NOT_ACTIVED(402, "Account Not Actived"),

    //访问拒绝
    ACCESS_DENIED(403, "Access Denied"),

    //数据库错误
    DB_ERROR(503, "Error Querying Database"),

    //参数错误
    PARAM_ERROR(501, "Parameter Error"),

    //参数为空
    PARAM_IS_NULL(502, "Parameter Is Null");

    private int code;

    private String msg;

    Status(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public String getMsg(String msg) {
        return String.format(this.msg, null == msg ? "" : msg);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
