package com.newland.lanhe.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色部门关联
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysRolesDepts对象", description="角色部门关联")
public class SysRolesDepts implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long roleId;

    private Long deptId;


}
