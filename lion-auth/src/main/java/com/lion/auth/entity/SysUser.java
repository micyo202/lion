package com.lion.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户实体类
 * </p>
 *
 * @author Yanzheng https://github.com/micyo202
 * @since 2020-02-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码（密文）
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别: true 男, false 女
     */
    private Boolean sex;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 可用性: true 可用, false 不可用
     */
    private Boolean enabled;

    /**
     * 过期性: true 没过期, false 过期
     */
    private Boolean accountNonExpired;

    /**
     * 有效性: true 凭证有效, false 凭证无效
     */
    private Boolean credentialsNonExpired;

    /**
     * 锁定性: true 未锁定, false 已锁定
     */
    private Boolean accountNonLocked;

    /**
     * 是否有效: true 有效/未删除, false 无效/已删除
     */
    private Boolean valid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
