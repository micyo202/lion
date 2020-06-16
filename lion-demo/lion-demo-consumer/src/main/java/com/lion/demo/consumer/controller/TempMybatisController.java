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
package com.lion.demo.consumer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lion.common.base.controller.BaseController;
import com.lion.common.result.Result;
import com.lion.common.util.DateUtil;
import com.lion.demo.consumer.entity.TempMybatis;
import com.lion.demo.consumer.service.TempMybatisService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * TempMybatisController
 * Mybatis示例
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/04/15
 */
@Api("Mybatis示例")
@RestController
@RequestMapping("/temp/mybatis")
@Slf4j
public class TempMybatisController extends BaseController {

    @Autowired
    private TempMybatisService tempMybatisService;

    @ApiOperation(value = "Mybatis插入数据", notes = "当 num > 5 时触发事务回滚")
    @ApiParam(name = "num", value = "插入数据条数", defaultValue = "5", required = true)
    @RequestMapping(value = "/save/{num}", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional
    public Result<String> save(@PathVariable int num) {

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
                tempMybatis.setId(DateUtil.getTimestamp());
            } else {
                //主键冲突，触发事务回滚
                tempMybatis.setId(1L);
            }

            // 若使用 Try Catch 需要手动回滚事务：TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

            // 纯手动方式控制事务
            /*
            @Autowired
            DataSourceTransactionManager dataSourceTransactionManager;
            @Autowired
            TransactionDefinition transactionDefinition;
            // 手动开启事务
            TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
            // 手动提交事务
            dataSourceTransactionManager.commit(transactionStatus);
            // 手动回滚事务
            dataSourceTransactionManager.rollback(transactionStatus);
            */

            tempMybatisService.save(tempMybatis);
        }
        return Result.success("temp_mybatis 数据保存成功，执行条数：" + num);
    }

    @ApiOperation("Mybatis自定义SQL方式查询")
    @RequestMapping(value = "/custom/sql", method = {RequestMethod.GET, RequestMethod.POST})
    public Result<List<TempMybatis>> customSql() {
        return Result.success(tempMybatisService.listByCustomSql());
    }

    @ApiOperation("Mybatis分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "version", value = "版本号（取值范围：v1~v6）", defaultValue = "v1", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "页码值", defaultValue = "1", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", defaultValue = "3", dataType = "String")
    })
    @RequestMapping(value = "/page/{version}/{pageNum}/{pageSize}", method = {RequestMethod.GET, RequestMethod.POST})
    public Result<PageInfo<TempMybatis>> page(@PathVariable String version, @PathVariable int pageNum, @PathVariable int pageSize) {

        String statement = "com.lion.demo.consumer.mapper.TempMybatisMapper.listByCustomSql";

        PageInfo<TempMybatis> pageInfo;

        String orderBy = "name DESC,id ASC";

        QueryWrapper<TempMybatis> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "Yanzheng");

        switch (version.toLowerCase()) {
            case "v1":
                pageInfo = tempMybatisService.page(pageNum, pageSize);
                break;
            case "v2":
                pageInfo = tempMybatisService.page(pageNum, pageSize, orderBy);
                break;
            case "v3":
                pageInfo = tempMybatisService.page(statement, pageNum, pageSize);
                break;
            case "v4":
                pageInfo = tempMybatisService.page(statement, pageNum, pageSize, orderBy);
                break;
            case "v5":
                pageInfo = tempMybatisService.page(queryWrapper, pageNum, pageSize);
                break;
            case "v6":
                pageInfo = tempMybatisService.page(queryWrapper, pageNum, pageSize, orderBy);
                break;
            default:
                pageInfo = null;
                break;
        }

        if (null == pageInfo) {
            return Result.failure("[version] 参数不正确，取值范围应为：v1~v6（例：/page/v1/1/10）");
        } else {
            return Result.success(pageInfo);
        }

    }
}