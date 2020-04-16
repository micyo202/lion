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
package com.lion.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lion.auth.entity.SysRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author Yanzheng https://github.com/micyo202
 * @since 2020-02-12
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户ID获取角色列表信息
     * 占位符风格
     */
    @Select("select r.* from sys_role r, sys_user_role ur where r.id=ur.role_id and r.valid=1 and ur.valid=1 and ur.user_id=#{0}")
    List<SysRole> getRoleByUserId(Integer userId);
}