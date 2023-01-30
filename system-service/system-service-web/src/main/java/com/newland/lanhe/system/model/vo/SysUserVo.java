package com.newland.lanhe.system.model.vo;

import com.newland.lanhe.system.entity.SysUser;
import lombok.Data;

import java.util.List;

/**
 * 用户列表接口返回信息
 * Author: leell
 * Date: 2022/12/7 01:05:30
 */
@Data
public class SysUserVo extends SysUser {
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 角色名称
     */
    private List<String> roleNames;
}
