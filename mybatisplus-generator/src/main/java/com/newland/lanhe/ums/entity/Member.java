package com.newland.lanhe.ums.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 会员
 * </p>
 *
 * @author leellun
 * @since 2023-01-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_member")
@ApiModel(value="Member对象", description="会员")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "描述")
    private String desc;

    @ApiModelProperty(value = "启用状态")
    private Integer status;

    @ApiModelProperty(value = "创建者")
    private Long createdBy;

    @ApiModelProperty(value = "修改者")
    private Long updatedBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime gmtModified;


}
