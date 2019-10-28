package com.lion.demo.provider.temp.controller;

import com.lion.common.entity.Result;
import com.lion.common.entity.ResultPage;
import com.lion.demo.provider.temp.entity.TempJpa;
import com.lion.demo.provider.temp.repository.TempRepository;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
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
public class TempJpaController {

    @Autowired
    private TempRepository tempRepository;

    @ApiOperation(value = "Jpa方式插入数据，带事物", notes = "该方法若要触发事物回滚，插入条数大于5即可")
    @ApiParam(name = "num", value = "插入数据条数", required = true)
    @RequestMapping(value = "/save/{num}", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional
    public Result jpaSave(@PathVariable int num) {

        if (0 >= num) {
            return Result.failure("参数必须是大于0的数字");
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
            tempRepository.save(tempJpa);
        }
        return Result.success("保存成功，条数：" + num);
    }


    @ApiOperation(value = "Jpa方式自定义SQL查询数据", notes = "参数有效标志，必填")
    @ApiImplicitParam(name = "status", value = "有效标志", dataType = "boolean", required = true)
    @RequestMapping(value = "/sql", method = {RequestMethod.GET, RequestMethod.POST})
    public Result jpaSql(@RequestParam(defaultValue = "true") boolean status) {
        List<TempJpa> list = tempRepository.selectByCustomSql(status);
        return Result.success(list);
    }

    @ApiOperation(value = "Jpa方式分页查询", notes = "分页默认从第0条数据开始，每页展示3条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码值", defaultValue = "0", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", defaultValue = "3", dataType = "String")
    })
    @RequestMapping(value = "/page", method = {RequestMethod.GET, RequestMethod.POST})
    public ResultPage jpaPage(@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "3") int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        Page page = tempRepository.findAll(pageable);
        return ResultPage.success(page);
    }

}
