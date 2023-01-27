package com.newland.lanhe.user.model.vo;

import com.newland.lanhe.user.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户详情
 * Author: leell
 * Date: 2022/12/7 14:16:15
 */
@Data
@ApiModel(value = "用户返回对象")
public class SysUserItemVo extends SysUser {
    @ApiModelProperty(value = "角色id")
    private List<String> roleIds;
    private String deptName;
}
