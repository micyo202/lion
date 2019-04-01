package com.lion.demo.provider.controller;

import com.lion.demo.provider.dao.UserJpaDao;
import com.lion.demo.provider.model.UserJpa;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * UserJpaController
 * Spring Data Jpa 示例代码
 *
 * @author Yanzheng
 * @date 2019/03/29
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Api("Jpa相关示例代码类说明文档")
@RestController
public class UserJpaController {

    @Autowired
    private UserJpaDao userJpaDao;

    @ApiOperation(value = "Jpa方式插入数据，带事物", notes = "该方法若要触发事物回滚，插入条数大于5即可")
    @ApiParam(name = "num", value = "插入数据条数", defaultValue = "2", required = true)
    @RequestMapping(value = "/jpa/save/{num}", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional
    public String jpaSave(@PathVariable int num) {
        if (num <= 0) {
            return "参数必须是大于0的数字";
        }

        for (int i = 0; i < num; i++) {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = null;
            try {
                birthday = df.parse("1999-08-09");
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String randomStr = Math.ceil(Math.random() * 100) + "";

            UserJpa userJpa = new UserJpa();
            userJpa.setUsername("username-" + randomStr);
            userJpa.setPassword("888888");
            userJpa.setBirthday(birthday);
            userJpa.setType(1);
            userJpa.setStatus(true);
            userJpa.setJointime(new Date());

            if (i < 5) {
                //正常插入
                userJpa.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            } else {
                //超出范围长度，触发事物回滚
                userJpa.setId(UUID.randomUUID().toString());
            }

            userJpaDao.save(userJpa);
        }

        return "保存成功，条数：" + num;
    }


    @ApiOperation(value = "Jpa方式自定义SQL查询数据", notes = "参数有效标志，必填")
    @ApiImplicitParam(name = "status", value = "有效标志", dataType = "boolean", required = true)
    @RequestMapping(value = "/jpa/sql", method = {RequestMethod.GET, RequestMethod.POST})
    public List<UserJpa> jpaSql(@RequestParam(defaultValue = "true") boolean status) {
        List<UserJpa> list = userJpaDao.findBySql(status);
        return list;
    }

    @ApiOperation(value = "Jpa方式分页查询", notes = "分页默认从第0条数据开始，每页展示6条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "页码值", defaultValue = "0", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", defaultValue = "6", dataType = "String")
    })
    @RequestMapping(value = "/jpa/page", method = {RequestMethod.GET, RequestMethod.POST})
    public Page<UserJpa> jpaPage(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "6") int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "jointime");
        Pageable pageable = PageRequest.of(offset, pageSize, sort);
        Page<UserJpa> page = userJpaDao.findAll(pageable);
        return page;
    }

}
