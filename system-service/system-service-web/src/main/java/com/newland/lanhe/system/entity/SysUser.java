package com.newland.lanhe.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.lanhe.model.BaseEntity;
import com.newland.lanhe.validator.Insert;
import com.newland.lanhe.validator.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author leellun
 * @since 2023-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysUser对象", description = "系统用户")
public class SysUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull(message = "部门名称不能为空",groups = {Insert.class, Update.class})
    @JsonSerialize(using= ToStringSerializer.class)
    @ApiModelProperty(value = "部门名称")
    private Long deptId;
    @JsonSerialize(using= ToStringSerializer.class)
    @ApiModelProperty(value = "岗位id")
    private Long jobId;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 性别
     *
     * @see com.newland.lanhe.system.enums.GenderEnum
     */
    @ApiModelProperty(value = "性别 1男 0女")
    private Integer gender;

    @Pattern(regexp = "^\\d{11}$",message ="手机号码格式不正确",groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "状态：1启用、0禁用")
    private Integer enabled;

    @ApiModelProperty(value = "是否可以删除：1可以、0不可以")
    private Integer canDeleted;

    /**
     * @see com.newland.lanhe.enumeration.BasicEnum
     */
    @ApiModelProperty(value = "是否必须修改密码：1是、0否")
    private Integer mustResetPwd;

    @ApiModelProperty(value = "密码连续错误次数")
    private Integer pwdFailsCount;

    @ApiModelProperty(value = "密码错误锁定时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime failLockTime;

    @ApiModelProperty(value = "修改密码的时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pwdResetTime;

    @ApiModelProperty(value = "最后一次登陆时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;

}
