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
package com.lion.demo.consumer.temp.service.impl;

import com.lion.common.base.service.impl.BaseServiceImpl;
import com.lion.demo.consumer.temp.entity.TempMybatis;
import com.lion.demo.consumer.temp.mapper.TempMybatisMapper;
import com.lion.demo.consumer.temp.service.ITempMybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Yanzheng https://github.com/micyo202
 * @since 2020-02-15
 */
@Service
public class TempMybatisServiceImpl extends BaseServiceImpl<TempMybatisMapper, TempMybatis> implements ITempMybatisService {

    @Autowired
    private TempMybatisMapper tempMybatisMapper;

    public List<TempMybatis> customSqlForMapper() {
        return tempMybatisMapper.selectByCustomSqlForMapper();
    }

    public List<TempMybatis> customSqlForXml() {
        return tempMybatisMapper.selectByCustomSqlForXml();
    }
}
