package com.newland.lanhe.user.model.vo;

import com.newland.lanhe.user.entity.SysDepartment;
import lombok.Data;

/**
 * 上级部门名称
 * Author: leell
 * Date: 2023/1/28 18:49:34
 */
@Data
public class SysDepartmentVo extends SysDepartment {
    private String parentName;
}
