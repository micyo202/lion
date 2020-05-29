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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.PageInfo;
import com.lion.common.constant.ResponseCode;
import com.lion.common.constant.ResponseStatus;
import com.lion.common.tuple.Tuple2;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.time.Instant;
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
@ApiModel(value = "结果实体类")
public class Result implements Serializable {

    /**
     * 状态值
     */
    @ApiModelProperty(value = "状态值")
    private int code = ResponseCode.SUCCESS;

    /**
     * 提示信息
     */
    @ApiModelProperty(value = "提示信息")
    private String msg = ResponseStatus.SUCCESS.msg();

    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private long timestamp = Instant.now().toEpochMilli();

    /**
     * 对象（仅用于对象传入转换，不会转json）
     */
    @JsonIgnore
    @ApiModelProperty(value = "对象")
    private Object obj;

    /**
     * 数据
     */
    @ApiModelProperty(value = "数据")
    private Object data;

    /**
     * 额外扩展数据
     */
    @ApiModelProperty(value = "额外扩展数据")
    private Map<String, Object> extra = null;

    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数")
    private Long total;

    /**
     * 页码值
     */
    @ApiModelProperty(value = "页码值")
    private Integer pageNum;

    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数")
    private Integer pageSize;

    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private Integer pages;

    /**
     * 自定义相关getter、setter方法
     */
    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
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

    public long getTimestamp() {
        return timestamp;
    }

    public Result setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Result setObj(Object obj) {
        this.obj = obj;

        if (obj instanceof Page) {
            // Jpa 分页对象
            Page p = (Page) obj;
            this.setTotal(p.getTotalElements());
            this.setPageNum(p.getPageable().getPageNumber() + 1);
            this.setPageSize(p.getPageable().getPageSize());
            this.setPages(p.getTotalPages());
            this.setData(p.getContent());
        } else if (obj instanceof IPage) {
            // MybatisPlus 分页对象
            IPage p = (com.baomidou.mybatisplus.extension.plugins.pagination.Page) obj;
            this.setTotal(p.getTotal());
            this.setPageNum((int) p.getCurrent());
            this.setPageSize((int) p.getSize());
            this.setPages((int) p.getPages());
            this.setData(p.getRecords());
        } else if (obj instanceof PageInfo) {
            // Pagehelper 分页对象
            PageInfo pageInfo = (PageInfo) obj;
            this.setTotal(pageInfo.getTotal());
            this.setPageNum(pageInfo.getPageNum());
            this.setPageSize(pageInfo.getPageSize());
            this.setPages(pageInfo.getPages());
            this.setData(pageInfo.getList());
        } else {
            // 一般对象（非分页对象）
            this.setData(obj);
        }

        return this;
    }

    public Object getData() {
        return data;
    }

    private void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public Result setExtra(Map<String, Object> extra) {
        this.extra = extra;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    private void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    private void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    private void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPages() {
        return pages;
    }

    private void setPages(Integer pages) {
        this.pages = pages;
    }

    /**
     * 自定义扩展方法
     */
    public Result addExtra(String key, Object value) {
        if (null == this.extra) {
            this.extra = new HashMap<>(8);
        }
        this.extra.put(key, value);
        return this;
    }

    public Result addExtra(Tuple2 tuple2) {
        if (null == this.extra) {
            this.extra = new HashMap<>(8);
        }
        this.extra.put(tuple2._1().toString(), tuple2._2());
        return this;
    }

    public Result setStatus(ResponseStatus responseStatus) {
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

    public static Result success(Object obj) {
        Result result = new Result();
        result.setObj(obj);
        return result;
    }

    public static Result success(Object obj, Map<String, Object> extra) {
        Result result = new Result();
        result.setObj(obj);
        result.setExtra(extra);
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