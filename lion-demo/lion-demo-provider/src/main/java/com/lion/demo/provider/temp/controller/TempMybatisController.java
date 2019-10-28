package com.lion.demo.provider.temp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lion.common.entity.Result;
import com.lion.common.entity.ResultPage;
import com.lion.demo.provider.temp.mapper.TempMybatisMapper;
import com.lion.demo.provider.temp.model.TempMybatis;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
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
public class TempMybatisController {

    @Autowired
    private TempMybatisMapper tempMybatisMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @ApiOperation(value = "使用Mybatis方式插入数据", notes = "包含事物")
    @ApiParam(name = "num", value = "插入数据条数", defaultValue = "3", required = true)
    @RequestMapping(value = "/save/{num}", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional
    public Result mybatisSave(@PathVariable int num) {

        if (0 >= num) {
            return Result.failure("参数必须是大于0的数字");
        }

        for (int i = 0; i < num; i++) {

            String randomStr = Math.ceil(Math.random() * 100) + "";

            TempMybatis tempMybatis = new TempMybatis();
            tempMybatis.setName("TempMybatisName-" + randomStr);
            tempMybatis.setType(9);
            tempMybatis.setStatus(false);
            tempMybatis.setCreateTime(new Date());
            tempMybatis.setUpdateTime(new Date());

            if (i + 1 < 6) {
                //正常插入
                tempMybatis.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            } else {
                //超出范围长度，触发事物回滚
                tempMybatis.setId(UUID.randomUUID().toString());
            }
            // 若使用 Try Catch 需要手动回滚事务：TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            tempMybatisMapper.insertSelective(tempMybatis);
        }
        return Result.success("保存成功，响应条数：" + num);
    }

    @ApiOperation("Mybatis自定义API接口，注解SQL方式查询")
    @RequestMapping(value = "/sql", method = {RequestMethod.GET, RequestMethod.POST})
    public Result mybatisCustomList() {
        List<TempMybatis> list = tempMybatisMapper.selectByCustomSqlForMapper();
        return Result.success(list);
    }

    @ApiOperation("Mybatis自定义SqlMap查询，并分页")
    @RequestMapping(value = "/page", method = {RequestMethod.GET, RequestMethod.POST})
    public ResultPage mybatisSqlPage() {
        PageHelper.offsetPage(0, 3);
        // List<TempMybatis> list = sqlSessionTemplate.selectList("com.lion.demo.provider.temp.mapper.TempMybatisMapper.selectByCustomSqlForXml");
        List<TempMybatis> list = tempMybatisMapper.selectByCustomSqlForXml();
        PageInfo pageInfo = new PageInfo<>(list);
        return ResultPage.success(pageInfo);
    }

}
