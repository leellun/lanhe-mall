package com.newland.lanhe.system.entity;

import com.newland.lanhe.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 岗位
 * </p>
 *
 * @author leellun
 * @since 2023-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysJob对象", description="岗位")
public class SysJob extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "岗位名称")
    private String name;

    /**
     * @see com.newland.lanhe.enumeration.BasicEnum
     */
    @ApiModelProperty(value = "岗位状态")
    private Integer enabled;

    @ApiModelProperty(value = "排序")
    private Integer jobSort;

}
