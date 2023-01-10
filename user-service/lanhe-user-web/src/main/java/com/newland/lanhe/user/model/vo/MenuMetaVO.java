package com.newland.lanhe.user.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单元数据
 */
@Data
@AllArgsConstructor
@ApiModel("菜单元数据")
public class MenuMetaVO implements Serializable {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("图标")
    private String icon;
}
