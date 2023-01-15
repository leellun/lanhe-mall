package com.newland.lanhe.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.user.entity.SysDepartment;
import com.newland.lanhe.user.entity.SysMenu;
import com.newland.lanhe.user.model.dto.DeptQueryDTO;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 部门 服务类
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
public interface SysDepartmentService extends IService<SysDepartment> {

    /**
     * 获取部门列表
     *
     * @param deptQueryDTO
     * @return
     */
    Page<SysDepartment> getDepartments(DeptQueryDTO deptQueryDTO);

    /**
     * 添加部门
     *
     * @param department
     */
    void addDepartment(SysDepartment department);

    /**
     * 更新部门
     *
     * @param department
     */

    void updateDepartment(SysDepartment department);

    /**
     * 删除部门
     *
     * @param ids
     */
    void deleteDepartment(Set<Long> ids);

    /**
     * 获取同级目录和上级目录
     *
     * @param ids
     * @return
     */
    List<SysDepartment> getSuperior(List<Long> ids);
}
