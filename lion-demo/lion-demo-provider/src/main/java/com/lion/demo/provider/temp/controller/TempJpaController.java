package com.lion.demo.provider.temp.controller;

import com.lion.common.base.controller.BaseController;
import com.lion.common.entity.Result;
import com.lion.common.entity.ResultPage;
import com.lion.demo.provider.temp.entity.TempJpa;
import com.lion.demo.provider.temp.service.TempJpaService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * TempJpaController
 * TODO
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/15
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Api("Jpa相关示例代码类说明文档")
@RestController
@RequestMapping("/temp/jpa")
@Slf4j
public class TempJpaController extends BaseController {

    @Autowired
    private TempJpaService tempJpaService;

    @ApiOperation(value = "Jpa方式插入数据", notes = "当 num > 5 时触发事务回滚")
    @ApiParam(name = "num", value = "插入数据条数", defaultValue = "5", required = true)
    @RequestMapping(value = "/save/{num}", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional
    public Result save(@PathVariable int num) {

        if (0 >= num) {
            return Result.failure("[num] 参数不正确，取值范围必须大于 0 的整数（例：/save/3）");
        }

        for (int i = 0; i < num; i++) {

            String randomStr = Math.ceil(Math.random() * 100) + "";

            TempJpa tempJpa = new TempJpa();
            tempJpa.setName("TempJpaName-" + randomStr);
            tempJpa.setType(6);
            tempJpa.setStatus(true);
            tempJpa.setCreateTime(new Date());
            tempJpa.setUpdateTime(new Date());

            if (i + 1 < 6) {
                //正常插入
                tempJpa.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            } else {
                //超出范围长度，触发事物回滚
                tempJpa.setId(UUID.randomUUID().toString());
            }

            // 若使用 Try Catch 需要手动回滚事务：TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            tempJpaService.insert(tempJpa);
        }

        return Result.success("temp_jpa 数据保存成功，执行条数：" + num);
    }


    @ApiOperation(value = "Jpa方式自定义SQL查询数据", notes = "参数为有效标志（必填）")
    @ApiImplicitParam(name = "status", value = "有效标志", dataType = "boolean", defaultValue = "true", required = true)
    @RequestMapping(value = "/custom/sql/{status}", method = {RequestMethod.GET, RequestMethod.POST})
    public Result customSql(@PathVariable boolean status) {
        return Result.success(tempJpaService.customSql(status));
    }

    @ApiOperation(value = "Jpa方式分页查询", notes = "分页默认从第0条数据开始，每页展示3条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码值", defaultValue = "1", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", defaultValue = "3", dataType = "String")
    })
    @RequestMapping(value = "/page/{version}/{pageNum}/{pageSize}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResultPage jpaPage(@PathVariable String version, @PathVariable int pageNum, @PathVariable int pageSize) {

        Page page;

        String orderBy = "name DESC,id ASC";

        TempJpa tempJpa = new TempJpa();
        tempJpa.setName("Yanzheng");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith());

        Example example = Example.of(tempJpa, matcher);

        switch (version.toLowerCase()) {
            case "v1":
                page = tempJpaService.findByPage(pageNum, pageSize);
                break;
            case "v2":
                page = tempJpaService.findByPage(pageNum, pageSize, orderBy);
                break;
            case "v3":
                page = tempJpaService.findByPage(example, pageNum, pageSize);
                break;
            case "v4":
                page = tempJpaService.findByPage(example, pageNum, pageSize, orderBy);
                break;
            default:
                page = null;
                break;
        }

        if (null == page) {
            return ResultPage.failure("[version] 参数不正确，取值范围应为：v1~v4（例：/page/v1/1/10）");
        } else {
            return ResultPage.success(page);
        }

    }

}
