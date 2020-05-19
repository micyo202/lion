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
package com.lion.demo.consumer.service.impl;

import com.lion.common.base.service.impl.BaseServiceImpl;
import com.lion.demo.consumer.entity.TempMybatis;
import com.lion.demo.consumer.mapper.TempMybatisMapper;
import com.lion.demo.consumer.service.TempMybatisService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * MyBatis示例表 服务实现类
 * </p>
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @since 2020-05-09
 */
@Service
public class TempMybatisServiceImpl extends BaseServiceImpl<TempMybatisMapper, TempMybatis> implements TempMybatisService {

    @Override
    public List<TempMybatis> listByCustomSql() {
        return baseMapper.listByCustomSql();
    }

    @Override
    public int insertByCustomSql(TempMybatis tempMybatis) {
        return baseMapper.insertByCustomSql(tempMybatis);
    }
}
