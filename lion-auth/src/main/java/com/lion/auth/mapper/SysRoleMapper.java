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