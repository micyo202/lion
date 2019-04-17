package com.lion.demo.provider.temp.controller;

import com.github.pagehelper.PageHelper;
import com.lion.demo.provider.temp.mapper.TempMybatisCustomMapper;
import com.lion.demo.provider.temp.mapper.TempMybatisMapper;
import com.lion.demo.provider.temp.model.TempMybatis;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * TempMybatisController
 * TODO
 *
 * @author Yanzheng
 * @date 2019/04/15
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Api("Mybatis相关示例代码类说明文档")
@RestController
public class TempMybatisController {

    @Autowired
    private TempMybatisMapper tempMybatisMapper;

    @Autowired
    private TempMybatisCustomMapper tempMybatisCustomMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @ApiOperation(value = "使用Mybatis方式插入数据", notes = "包含事物")
    @ApiParam(name = "isRollback", value = "是否触发事物回滚", defaultValue = "false", required = true)
    @RequestMapping(value = "/mybatis/save", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional
    public String mybatisSave(@RequestParam(defaultValue = "false") boolean isRollback) {

        String id = UUID.randomUUID().toString();
        if (!isRollback) {
            id = id.replaceAll("-", "");
        }

        String randomStr = Math.ceil(Math.random() * 100) + "";

        TempMybatis tempMybatis = new TempMybatis();
        tempMybatis.setId(id);
        tempMybatis.setName("name-" + randomStr);
        tempMybatis.setType(9);
        tempMybatis.setStatus(true);
        tempMybatis.setCreateTime(new Date());
        tempMybatis.setUpdateTime(new Date());

        tempMybatisMapper.insertSelective(tempMybatis);

        return "插入成功";
    }

    @ApiOperation("Mybatis自定义API接口，注解SQL方式查询")
    @RequestMapping(value = "/mybatis/custom/list", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Object> mybatisCustomList() {
        List<Object> list = tempMybatisCustomMapper.selectAll();
        return list;
    }

    @ApiOperation("Mybatis自定义sqlMap查询，并分页")
    @RequestMapping(value = "/mybatis/sql/page", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Object> mybatisSqlPage() {
        PageHelper.offsetPage(0, 5);
        List<Object> list = sqlSessionTemplate.selectList("com.lion.demo.provider.mapper.temp.TempMybatisCustomMapper.getAll");
        return list;
    }

}
