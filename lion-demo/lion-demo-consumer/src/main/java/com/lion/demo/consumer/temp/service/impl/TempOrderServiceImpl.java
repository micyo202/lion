package com.lion.demo.consumer.temp.service.impl;

import com.lion.demo.consumer.temp.entity.TempOrder;
import com.lion.demo.consumer.temp.mapper.TempOrderMapper;
import com.lion.demo.consumer.temp.service.ITempOrderService;
import com.lion.common.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Yanzheng https://github.com/micyo202
 * @since 2020-03-30
 */
@Service
public class TempOrderServiceImpl extends BaseServiceImpl<TempOrderMapper, TempOrder> implements ITempOrderService {

}
