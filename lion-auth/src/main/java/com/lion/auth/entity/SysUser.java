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
package com.lion.auth.entity;

import com.lion.common.base.entity.BaseEntity;
import java.time.LocalDate;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @since 2020-05-09
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SysUser对象", description="用户表")
public class SysUser extends BaseEntity<SysUser> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "出生日期")
    private LocalDate birthday;

    @ApiModelProperty(value = "性别")
    private Boolean sex;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "启用标志（0：不启用，1：启用）")
    private Boolean enabled;

    @ApiModelProperty(value = "账户不过期（0：过期，1：正常）")
    private Boolean accountNonExpired;

    @ApiModelProperty(value = "凭证不过期（0：过期，1：正常）")
    private Boolean credentialsNonExpired;

    @ApiModelProperty(value = "账户不锁定（0：锁定，1：正常）")
    private Boolean accountNonLocked;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
