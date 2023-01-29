package com.newland.lanhe.user.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.lanhe.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统菜单
 * </p>
 *
 * @author leellun
 * @since 2023-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysMenu对象", description="系统菜单")
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "上级菜单ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long pid;

    @ApiModelProperty(value = "子菜单数目")
    private Integer subCount;

    /**
     * @see com.newland.lanhe.user.enums.MenuTypeEnum
     */
    @ApiModelProperty(value = "菜单类型")
    private Integer type;

    @ApiModelProperty(value = "菜单标题")
    private String title;

    @ApiModelProperty(value = "组件名称")
    private String name;

    @ApiModelProperty(value = "组件")
    private String component;

    @ApiModelProperty(value = "排序")
    private Integer menuSort;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "链接地址")
    private String path;


    @ApiModelProperty(value = "是否外链")
    private Integer target;

    /**
     * 1 启用 0 禁用
     * @see com.newland.lanhe.enumeration.BasicEnum
     */
    @ApiModelProperty(value = "启用状态")
    private Integer enabled;
    /**
     * 1 缓存 0 不缓存
     * @see com.newland.lanhe.enumeration.BasicEnum
     */
    @ApiModelProperty(value = "缓存")
    private Integer keepAlive;

    /**
     * 1 隐藏 0不隐藏
     * @see com.newland.lanhe.enumeration.BasicEnum
     */
    @ApiModelProperty(value = "隐藏")
    private Integer hidden;

    @ApiModelProperty(value = "权限")
    private String permission;

}
