package com.lion.demo.consumer.temp.service;

import com.lion.common.base.service.IBaseService;
import com.lion.demo.consumer.temp.entity.TempMybatis;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Yanzheng https://github.com/micyo202
 * @since 2020-02-15
 */
public interface ITempMybatisService extends IBaseService<TempMybatis> {

    List<TempMybatis> customSqlForMapper();

    List<TempMybatis> customSqlForXml();
}
