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
package com.lion.demo.consumer.service;

import com.lion.common.base.service.BaseService;
import com.lion.demo.consumer.entity.TempMybatis;

import java.util.List;

/**
 * <p>
 * MyBatis示例表 服务类
 * </p>
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @since 2020-05-09
 */
public interface TempMybatisService extends BaseService<TempMybatis> {

    List<TempMybatis> listByCustomSql();

    int insertByCustomSql(TempMybatis tempMybatis);
}
