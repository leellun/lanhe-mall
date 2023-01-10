package com.newland.lanhe.user.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 构建前端路由时用到
 */
@ApiModel(value = "前端路由")
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuVO implements Serializable {

    @ApiModelProperty("路由name")
    private String name;

    @ApiModelProperty("路由路径")
    private String path;

    @ApiModelProperty(value = "是否隐藏")
    private Boolean hidden;
    @ApiModelProperty(value = "重定向路径")
    private String redirect;
    @ApiModelProperty(value = "组件位置")
    private String component;
    @ApiModelProperty(value = "元数据")
    private MenuMetaVO meta;
    @ApiModelProperty(value = "子菜单")
    private List<MenuVO> children;
}
