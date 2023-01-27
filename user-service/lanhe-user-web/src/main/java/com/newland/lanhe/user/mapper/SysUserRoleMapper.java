package com.newland.lanhe.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newland.lanhe.user.entity.SysRole;
import com.newland.lanhe.user.entity.SysUserRole;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * <p>
 * 用户角色关联 Mapper 接口
 * </p>
 *
 * @author leellun
 * @since 2023-01-14
 */
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

}
