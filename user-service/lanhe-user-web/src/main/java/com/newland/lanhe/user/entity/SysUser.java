package com.newland.lanhe.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysUser对象", description = "系统用户")
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "部门名称")
    private Long deptId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * {@link com.newland.lanhe.enumeration.BasicEnum}
     */
    @ApiModelProperty(value = "性别 1男 0女")
    private Integer gender;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "账号类型")
    private Integer accountType;

    /**
     * {@link com.newland.lanhe.enumeration.BasicEnum}
     */
    @ApiModelProperty(value = "状态：1启用、0禁用")
    private Integer enabled;

    /**
     * {@link com.newland.lanhe.enumeration.BasicEnum}
     */
    @ApiModelProperty(value = "是否必须修改密码：1是、0否")
    private Integer mustResetPwd;

    @ApiModelProperty(value = "密码连续错误次数")
    private Integer pwdFailsCount;
    @ApiModelProperty(value = "密码错误锁定时间")
    private LocalDateTime failLockTime;
    @ApiModelProperty(value = "修改密码的时间")
    private LocalDateTime pwdResetTime;

    @ApiModelProperty(value = "最后一次登陆时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "创建者")
    private Long createdBy;

    @ApiModelProperty(value = "修改者")
    private Long updatedBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime gmtModified;


}
