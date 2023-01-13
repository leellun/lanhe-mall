package com.newland.lanhe.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newland.lanhe.user.entity.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 系统菜单 Mapper 接口
 * </p>
 *
 * @author leellun
 * @since 2023-01-14
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 获取角色权限
     * @param ids 角色id列表
     * @return
     */
    List<String> getPermissions(@Param("ids") List<Long> ids);
}
