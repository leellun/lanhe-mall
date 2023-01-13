package com.newland.lanhe.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newland.lanhe.user.entity.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 获取用户角色
     * @param userId 用户id
     * @return
     */
    List<SysRole> getRoleNameWithIdByUserId(Long userId);
}
