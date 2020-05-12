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
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @since 2020-05-09
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SysMenu对象", description="菜单表")
public class SysMenu extends BaseEntity<SysMenu> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "父主键")
    private Long pId;

    @ApiModelProperty(value = "父编码")
    private String pCode;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "请求地址")
    private String url;

    @ApiModelProperty(value = "层级")
    private Integer level;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "是否菜单（0：否，1：是）")
    private Boolean isMenu;

    @ApiModelProperty(value = "图标")
    private String icon;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
