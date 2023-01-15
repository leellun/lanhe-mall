package com.newland.lanhe.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.user.entity.SysDepartment;
import com.newland.lanhe.user.entity.SysMenu;
import com.newland.lanhe.user.enums.UserServiceErrorEnum;
import com.newland.lanhe.user.mapper.SysDepartmentMapper;
import com.newland.lanhe.user.model.dto.DeptQueryDTO;
import com.newland.lanhe.user.service.SysDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.utils.AssertUtil;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
@Service
public class SysDepartmentServiceImpl extends ServiceImpl<SysDepartmentMapper, SysDepartment> implements SysDepartmentService {

    @Override
    public Page<SysDepartment> getDepartments(DeptQueryDTO deptQueryDTO) {
        Page<SysDepartment> page = PageWrapper.wrapper(deptQueryDTO);
        return baseMapper.selectPage(page, Wrappers.lambdaQuery());
    }

    @Override
    public void addDepartment(SysDepartment department) {
        baseMapper.insert(department);
    }

    @Override
    public void updateDepartment(SysDepartment department) {
        SysDepartment dbDepartment = baseMapper.selectById(department.getId());
        AssertUtil.notNull(dbDepartment, UserServiceErrorEnum.DEPARTMENT_NOT_EXIST);
        department.setId(dbDepartment.getId());
        baseMapper.updateById(dbDepartment);
    }

    @Override
    public void deleteDepartment(Set<Long> ids) {
        int count = baseMapper.deleteBatchIds(ids);
        AssertUtil.isTrue(count > 0, UserServiceErrorEnum.DEPARTMENT_DELETE_FAIL);
    }

    @Override
    public List<SysDepartment> getSuperior(List<Long> ids) {
        List<SysDepartment> list = new ArrayList<>();
        List<Long> tempIds = ids;
        boolean pidNull = false;
        for (int i = 0; i < 2; i++) {
            List<SysDepartment> pDepts = baseMapper.selectList(Wrappers.<SysDepartment>lambdaQuery().select(SysDepartment::getPid).select(SysDepartment::getId).in(SysDepartment::getId, tempIds));
            List<SysDepartment> newDepts = new ArrayList<>();
            for (SysDepartment department : pDepts) {
                if (department.getPid() == null) {
                    if (!pidNull) {
                        pidNull = true;
                        newDepts.addAll(baseMapper.selectList(Wrappers.<SysDepartment>lambdaQuery().isNull(SysDepartment::getPid)));
                    }
                } else {
                    newDepts.addAll(baseMapper.selectList(Wrappers.<SysDepartment>lambdaQuery().eq(SysDepartment::getPid, department.getPid())));
                }
            }
            list.addAll(newDepts);
            tempIds = pDepts.stream().filter(item -> item.getPid() != null).map(SysDepartment::getPid).collect(Collectors.toList());
        }

        return list;
    }

}
