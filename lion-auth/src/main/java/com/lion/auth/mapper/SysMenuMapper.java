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
import com.lion.auth.entity.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author Yanzheng https://github.com/micyo202
 * @since 2020-02-12
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据角色ID获取菜单列表信息
     * SPEL表达式风格
     */
    @Select("select m.* from sys_menu m,sys_role_menu rm where m.id=rm.menu_id and m.valid=1 and rm.valid=1 and rm.role_id=#{roleId}")
    List<SysMenu> getMenuByRoleId(Integer roleId);

    /**
     * 根据角色Ids获取菜单列表信息
     * SPEL表达式风格
     */
    @Select({
            "<script>",
            "select m.* from sys_menu m,sys_role_menu rm where m.id=rm.menu_id and m.valid=1 and rm.valid=1 and rm.role_id in",
            "<foreach collection='roleIds' item='item' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    List<SysMenu> getMenuByRoleIds(@Param("roleIds") List<Integer> roleIds);
}