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
package com.lion.demo.provider.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lion.common.base.controller.BaseController;
import com.lion.common.exception.LionException;
import com.lion.common.result.Result;
import com.lion.demo.provider.entity.TempProduct;
import com.lion.demo.provider.service.TempProductService;
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
 * @author Yanzheng (https://github.com/micyo202)
 * @since 2020-03-30
 */
@Api("分布式事物示例-产品库存")
@RestController
@RequestMapping("/temp/product")
public class TempProductController extends BaseController {

    @Autowired
    private TempProductService tempProductService;

    @ApiOperation(value = "扣减产品库存", notes = "当库存不足时，扣减失败，回滚")
    @RequestMapping(value = "/deduct", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional(rollbackFor = Exception.class)
    public Result<String> deduct(String productCode, int count) {

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