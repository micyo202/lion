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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author Yanzheng https://github.com/micyo202
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/temp/order")
public class TempOrderController extends BaseController {

    @Autowired
    private ITempOrderService tempOrderService;

    @Autowired
    private ProviderDemoClient providerDemoClient;

    /**
     * 下单：插入订单表、扣减库存，正常下单
     */
    @RequestMapping("/commit")
    @GlobalTransactional
    @Transactional
    public Result commit() {
        place("product-1", 1);
        return Result.success();
    }

    /**
     * 下单：插入订单表、扣减库存，模拟回滚
     */
    @RequestMapping("/rollback")
    @GlobalTransactional
    @Transactional
    public Result rollback() {
        place("product-1", 1);
        throw new LionException("下单：插入订单表、扣减库存，模拟回滚（有全局事务，已回滚，数据正常）...");
    }

    /**
     * 下单：插入订单表、扣减库存，错误但不会滚
     */
    @RequestMapping("/exception")
    @Transactional
    public Result exception() {
        place("product-1", 1);
        throw new LionException("下单：插入订单表、扣减库存，模拟异常（无全局事务，没有回滚，数据已错乱，）...");
    }

    private void place(String productCode, int count) {
        TempOrder tempOrder = new TempOrder()
                .setProductCode(productCode)
                .setCount(count)
                .setValid(1)
                .setCreateTime(DateUtil.getCurrentLocalDateTime())
                .setUpdateTime(DateUtil.getCurrentLocalDateTime());
        tempOrderService.save(tempOrder);
        Result deduct = providerDemoClient.deduct(productCode, count);
        if (deduct.getCode() != ResponseCode.SUCCESS) {
            throw new LionException(deduct.getMsg());
        }
    }

}

