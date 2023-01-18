package com.newland.lanhe.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.lanhe.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 部门
 * </p>
 *
 * @author leellun
 * @since 2023-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysDepartment对象", description="部门")
public class SysDepartment extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @JsonSerialize(using= ToStringSerializer.class)
    @ApiModelProperty(value = "上级部门")
    private Long pid;

    @ApiModelProperty(value = "子部门数目")
    private Integer subCount;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "排序")
    private Integer deptSort;

    /**
     * 1 启用 0 未启用
     * @see com.newland.lanhe.enumeration.BasicEnum
     */
    @ApiModelProperty(value = "状态")
    private Integer enabled;

    @TableField(exist = false)
    private List<SysDepartment> children;
}
