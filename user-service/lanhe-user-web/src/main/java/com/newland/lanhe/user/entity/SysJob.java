package com.newland.lanhe.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.newland.lanhe.constant.Constant;
import com.newland.lanhe.enumeration.BasicEnum;
import com.newland.lanhe.validator.Insert;
import com.newland.lanhe.validator.IntOptions;
import com.newland.lanhe.validator.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 岗位
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysJob对象", description = "岗位")
public class SysJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotEmpty(message = "岗位名称不能为空", groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "岗位名称")
    private String name;

    @IntOptions(message = "岗位状态不正确", options = {Constant.FLAG_YES, Constant.FLAG_NO})
    @ApiModelProperty(value = "岗位状态")
    private Integer enabled;

    @ApiModelProperty(value = "排序")
    private Integer jobSort;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "修改者")
    private String updatedBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime gmtModified;

    public static void main(String[] args) {

    }

}
