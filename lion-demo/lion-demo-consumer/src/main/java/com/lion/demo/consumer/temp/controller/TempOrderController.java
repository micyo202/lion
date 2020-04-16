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
package com.lion.demo.consumer.temp.controller;

import com.lion.common.base.controller.BaseController;
import com.lion.common.constant.ResponseCode;
import com.lion.common.entity.Result;
import com.lion.common.exception.LionException;
import com.lion.common.util.DateUtil;
import com.lion.demo.consumer.client.ProviderDemoClient;
import com.lion.demo.consumer.temp.entity.TempOrder;
import com.lion.demo.consumer.temp.service.ITempOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author Yanzheng https://github.com/micyo202
 * @since 2020-03-30
 */
@Api("分布式事物示例-订单")
@RestController
@RequestMapping("/temp/order")
public class TempOrderController extends BaseController {

    @Autowired
    private ITempOrderService tempOrderService;

    @Autowired
    private ProviderDemoClient providerDemoClient;

    @ApiOperation(value = "全局事务，正常下单", notes = "执行：插入订单表、扣减库存表")
    @RequestMapping(value = "/commit", method = {RequestMethod.GET, RequestMethod.POST})
    @GlobalTransactional
    @Transactional
    public Result commit() {
        place("product-1", 1);
        return Result.success("下单成功");
    }

    /**
     * 下单：插入订单表、扣减库存，模拟回滚
     */
    @ApiOperation(value = "全局事务，模拟回滚", notes = "执行：插入订单表、扣减库存表")
    @RequestMapping(value = "/rollback", method = {RequestMethod.GET, RequestMethod.POST})
    @GlobalTransactional
    @Transactional
    public Result rollback() {
        place("product-1", 1);
        throw new LionException("全局事务 -> 模拟回滚（数据正常）...");
    }

    /**
     * 下单：插入订单表、扣减库存，错误但不会滚
     */
    @ApiOperation(value = "无全局事务，无法回滚，数据错乱", notes = "执行：插入订单表、扣减库存表")
    @RequestMapping(value = "/exception", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional
    public Result exception() {
        place("product-1", 1);
        throw new LionException("无全局事务 -> 无法回滚（数据已错乱）...");
    }

    /**
     * 下单方法
     */
    private void place(String productCode, int count) {
        TempOrder tempOrder = new TempOrder()
                .setProductCode(productCode)
                .setCount(count)
                .setValid(1)
                .setCreateTime(DateUtil.getCurrentLocalDateTime())
                .setUpdateTime(DateUtil.getCurrentLocalDateTime());
        tempOrderService.save(tempOrder);
        Result deduct = providerDemoClient.deductFromProvider(productCode, count);
        if (deduct.getCode() != ResponseCode.SUCCESS) {
            throw new LionException(deduct.getMsg());
        }
    }

}

