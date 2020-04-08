package com.lion.demo.provider.temp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lion.common.base.controller.BaseController;
import com.lion.common.entity.Result;
import com.lion.common.exception.LionException;
import com.lion.demo.provider.temp.entity.TempProduct;
import com.lion.demo.provider.temp.service.ITempProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 产品表 前端控制器
 * </p>
 *
 * @author Yanzheng https://github.com/micyo202
 * @since 2020-03-30
 */
@Api("分布式事物示例-产品库存")
@RestController
@RequestMapping("/temp/product")
public class TempProductController extends BaseController {

    @Autowired
    private ITempProductService tempProductService;

    @ApiOperation(value = "扣减产品库存", notes = "当库存不足时，扣减失败，回滚")
    @RequestMapping(value = "/deduct", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional
    public Result deduct(String productCode, int count) {

        QueryWrapper<TempProduct> wrapper = new QueryWrapper<>();
        wrapper.setEntity(new TempProduct().setCode(productCode));
        TempProduct tempProduct = tempProductService.getOne(wrapper);
        tempProduct.setCount(tempProduct.getCount() - count);
        if (tempProduct.getCount() < 0) {
            throw new LionException(productCode + " 商品库存不足，扣减库存操作失败...");
        }
        tempProductService.updateById(tempProduct);

        return Result.success();
    }

}

