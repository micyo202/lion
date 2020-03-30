package com.lion.demo.provider.temp.service.impl;

import com.lion.demo.provider.temp.entity.TempProduct;
import com.lion.demo.provider.temp.mapper.TempProductMapper;
import com.lion.demo.provider.temp.service.ITempProductService;
import com.lion.common.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品表 服务实现类
 * </p>
 *
 * @author Yanzheng https://github.com/micyo202
 * @since 2020-03-30
 */
@Service
public class TempProductServiceImpl extends BaseServiceImpl<TempProductMapper, TempProduct> implements ITempProductService {

}
