package com.newland.lanhe.user.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Author: leell
 * Date: 2022/12/7 14:16:15
 */
@Data
@ApiModel(value = "用户列表返回对象")
public class SysUserDto {
    @ApiModelProperty(value = "用户id")
    private Long id;
    @ApiModelProperty(value = "角色id")
    private List<Long> roleIds;

    @ApiModelProperty(value = "部门id")
    private Long departmentId;
    @ApiModelProperty(value = "岗位id")
    private Long jobId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("性别")
    private Integer gender;
    @ApiModelProperty("头像")
    private String avatar;

    private Integer enabled;
}
