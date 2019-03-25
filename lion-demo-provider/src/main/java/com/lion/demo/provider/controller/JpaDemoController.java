package com.lion.demo.provider.controller;

import com.lion.demo.provider.dao.JpaDemoDao;
import com.lion.demo.provider.model.JpaDemo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * JpaDemoController
 * Spring Data Jpa 示例代码
 *
 * @author Yanzheng 严正
 * @date 2019/01/08
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Api("Jpa相关示例代码类说明文档")
@RestController
public class JpaDemoController {

    @Autowired
    private JpaDemoDao jpaDemoDao;

    @ApiOperation(value = "Jpa方式插入数据，带事物", notes = "该方法若要触发事物回滚，插入条数大于5即可")
    @ApiParam(name = "num", value = "插入数据条数", defaultValue = "2", required = true)
    @RequestMapping(value = "/jpa/save/{num}", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional
    public String jpaSave(@PathVariable int num) {
        if (num <= 0) {
            return "参数必须是大于0的数字";
        }

        for (int i = 0; i < num; i++) {

            String randomStr = Math.ceil(Math.random() * 100) + "";

            JpaDemo jpaDemo = new JpaDemo();
            jpaDemo.setUsername("demo-" + randomStr);
            jpaDemo.setPassword("888888");
            jpaDemo.setName("姓名-" + randomStr);
            jpaDemo.setSex(false);
            jpaDemo.setAge(20);
            jpaDemo.setJointime(new Date());
            jpaDemo.setType("demo");
            jpaDemo.setValid(true);

            if (i < 5) {
                //正常插入
                jpaDemo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            } else {
                //超出范围长度，触发事物回滚
                jpaDemo.setId(UUID.randomUUID().toString());
            }

            jpaDemoDao.save(jpaDemo);
        }

        return "保存成功，条数：" + num;
    }


    @ApiOperation(value = "Jpa方式自定义SQL查询数据", notes = "参数有效标志，必填")
    @ApiImplicitParam(name = "valid", value = "有效标志", dataType = "boolean", required = true)
    @RequestMapping(value = "/jpa/sql", method = {RequestMethod.GET, RequestMethod.POST})
    public List<JpaDemo> jpaSql(@RequestParam(defaultValue = "true") boolean valid) {
        List<JpaDemo> list = jpaDemoDao.findBySql(valid);
        return list;
    }

    @ApiOperation(value = "Jpa方式分页查询", notes = "分页默认从第0条数据开始，每页展示6条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "页码值", defaultValue = "0", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", defaultValue = "6", dataType = "String")
    })
    @RequestMapping(value = "/jpa/page", method = {RequestMethod.GET, RequestMethod.POST})
    public Page<JpaDemo> jpaPage(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "6") int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "jointime");
        Pageable pageable = PageRequest.of(offset, pageSize, sort);
        Page<JpaDemo> page = jpaDemoDao.findAll(pageable);
        return page;
    }

}