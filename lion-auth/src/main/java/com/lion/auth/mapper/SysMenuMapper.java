package com.lion.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lion.auth.entity.SysMenu;
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
}