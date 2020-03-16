package com.lion.demo.consumer.temp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lion.common.base.controller.BaseController;
import com.lion.common.entity.Result;
import com.lion.common.entity.ResultPage;
import com.lion.demo.consumer.temp.entity.TempMybatis;
import com.lion.demo.consumer.temp.service.ITempMybatisService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * TempMybatisController
 * TODO
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/15
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Api("Mybatis相关示例代码类说明文档")
@RestController
@RequestMapping("/temp/mybatis")
@Slf4j
public class TempMybatisController extends BaseController {

    @Autowired
    private ITempMybatisService tempMybatisService;

    @ApiOperation(value = "使用Mybatis方式插入数据", notes = "当 num > 5 时触发事务回滚")
    @ApiParam(name = "num", value = "插入数据条数", defaultValue = "5", required = true)
    @RequestMapping(value = "/save/{num}", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional
    public Result save(@PathVariable int num) {

        if (0 >= num) {
            return Result.failure("[num] 参数不正确，取值范围必须大于 0 的整数（例：/save/3）");
        }

        for (int i = 0; i < num; i++) {

            String randomStr = Math.ceil(Math.random() * 100) + "";

            TempMybatis tempMybatis = new TempMybatis();
            tempMybatis.setName("TempMybatisName-" + randomStr);
            tempMybatis.setValid(true);
            tempMybatis.setCreateTime(LocalDateTime.now());
            tempMybatis.setUpdateTime(LocalDateTime.now());

            if (i + 1 < 6) {
                //正常插入
                tempMybatis.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            } else {
                //超出范围长度，触发事物回滚
                tempMybatis.setId(UUID.randomUUID().toString());
            }

            // 若使用 Try Catch 需要手动回滚事务：TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            tempMybatisService.save(tempMybatis);
        }
        return Result.success("temp_mybatis 数据保存成功，执行条数：" + num);
    }

    @ApiOperation("Mybatis自定义API接口，注解SQL、XML方式查询")
    @RequestMapping(value = "/custom/sql/{type}", method = {RequestMethod.GET, RequestMethod.POST})
    public Result customSql(@PathVariable String type) {

        if (type.equalsIgnoreCase("mapper")) {
            return Result.success(tempMybatisService.customSqlForMapper());
        }

        if (type.equalsIgnoreCase("xml")) {
            return Result.success(tempMybatisService.customSqlForXml());
        }

        return Result.failure("[type] 参数不正确，取值范围应为：mapper、xml（例：/custom/mapper）");
    }

    @ApiOperation("Mybatis分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "version", value = "版本号（取值范围：v1~v6）", defaultValue = "v1", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "页码值", defaultValue = "1", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", defaultValue = "3", dataType = "String")
    })
    @RequestMapping(value = "/page/{version}/{pageNum}/{pageSize}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResultPage page(@PathVariable String version, @PathVariable int pageNum, @PathVariable int pageSize) {

        String statement = "com.lion.demo.consumer.temp.mapper.TempMybatisMapper.selectByCustomSqlForXml";

        PageInfo pageInfo;

        String orderBy = "name DESC,id ASC";

        QueryWrapper<TempMybatis> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "Yanzheng");

        switch (version.toLowerCase()) {
            case "v1":
                pageInfo = tempMybatisService.selectAllByPage(pageNum, pageSize);
                break;
            case "v2":
                pageInfo = tempMybatisService.selectAllByPage(pageNum, pageSize, orderBy);
                break;
            case "v3":
                pageInfo = tempMybatisService.selectByStatmentPage(statement, pageNum, pageSize);
                break;
            case "v4":
                pageInfo = tempMybatisService.selectByStatmentPage(statement, pageNum, pageSize, orderBy);
                break;
            case "v5":
                pageInfo = tempMybatisService.selectByWrapperPage(queryWrapper, pageNum, pageSize);
                break;
            case "v6":
                pageInfo = tempMybatisService.selectByWrapperPage(queryWrapper, pageNum, pageSize, orderBy);
                break;
            default:
                pageInfo = null;
                break;
        }

        if (null == pageInfo) {
            return ResultPage.failure("[version] 参数不正确，取值范围应为：v1~v6（例：/page/v1/1/10）");
        } else {
            return ResultPage.success(pageInfo);
        }

    }
}

