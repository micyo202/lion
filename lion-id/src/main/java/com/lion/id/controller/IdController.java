package com.lion.id.controller;

import com.lion.common.base.controller.BaseController;
import com.lion.common.exception.LionException;
import com.lion.id.entity.SysId;
import com.lion.id.service.IdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * IdController
 * 双 buffer 自增序列生成
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/28
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Api("双buffer自增序列生成")
@RestController
public class IdController extends BaseController {

    private static int buffer = 1;

    private static SysId sysId = new SysId();
    private static SysId sysId_buffer_1 = new SysId();  // buffer_1
    private static SysId sysId_buffer_2 = new SysId();  // buffer_2
    private static Future<SysId> futureSysId;

    private static int maxId;
    private static int step;
    private static boolean isChange;

    private static final String BIZ_TAG = "user_tag";

    @Autowired
    private IdService idService;

    @ApiOperation("自增序列生成方法")
    @RequestMapping(value = "/generator", method = {RequestMethod.GET, RequestMethod.POST})
    public Integer generator() {

        Integer id;

        // 初始化 或 超出 Range 范围时，从数据库获取 Range
        if (null == sysId.getId()) {
            buffer = 1;
            sysId_buffer_1 = idService.getSynSysId(BIZ_TAG);
            maxId = sysId_buffer_1.getMaxId();
            step = sysId_buffer_1.getStep();
            BeanUtils.copyProperties(sysId_buffer_1, sysId);
        }

        if (sysId.getMaxId() >= sysId.getStep()) {
            isChange = true;
            if (1 == buffer) {
                buffer = 2;
            } else {
                buffer = 1;
            }
        }

        if (1 == buffer && isChange) {
            isChange = false;
            try {
                sysId_buffer_1 = futureSysId.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new LionException(100, e.getMessage());
            }

            maxId = sysId_buffer_1.getMaxId();
            step = sysId_buffer_1.getStep();
            BeanUtils.copyProperties(sysId_buffer_1, sysId);
        }
        if (2 == buffer && isChange) {
            isChange = false;
            try {
                sysId_buffer_2 = futureSysId.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new LionException(100, e.getMessage());
            }

            maxId = sysId_buffer_2.getMaxId();
            step = sysId_buffer_2.getStep();
            BeanUtils.copyProperties(sysId_buffer_2, sysId);
        }

        id = sysId.getMaxId() + 1;
        sysId.setMaxId(id);

        int increaseRange = step - maxId;
        int increaseCount = step / (step - maxId);

        int increaseRate = (int) (increaseRange * .5f); // 50%

        int threshold = increaseRange * (increaseCount - 1) + increaseRate;

        if (id == threshold) {
            if (1 == buffer) {
                futureSysId = idService.getAsynSysId(BIZ_TAG);
            }
            if (2 == buffer) {
                futureSysId = idService.getAsynSysId(BIZ_TAG);
            }
        }

        return id;
    }

}
