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

import java.util.*;
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
    public List<SysDepartment> getDepartSearch(String name) {
        List<SysDepartment> list = baseMapper.selectList(Wrappers.<SysDepartment>lambdaQuery().like(SysDepartment::getName, name));
        Map<Long, SysDepartment> map = new HashMap<>();
        List<SysDepartment> datas = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            SysDepartment department = list.get(i);
            while (department.getPid() != null && department.getPid() != 0) {
                List<SysDepartment> children = new ArrayList<>();
                children.add(department);
                if (map.containsKey(department.getPid())) {
                    SysDepartment parentDept = map.get(department.getPid());
                    parentDept.getChildren().add(department);
                    break;
                } else {
                    department = baseMapper.selectById(department.getPid());
                    department.setChildren(children);
                    map.put(department.getId(), department);
                }
            }
            if ((department.getPid() == null || department.getPid() == 0) && map.get(department.getId()) == department) {
                datas.add(department);
            }
        }
        return datas;
    }

    @Override
    public List<SysDepartment> getSubDepts(Long pid) {
        if (pid == null || pid == 0) {
            return baseMapper.selectList(Wrappers.<SysDepartment>lambdaQuery().isNull(SysDepartment::getPid).orderByAsc(SysDepartment::getDeptSort));
        } else {
            return baseMapper.selectList(Wrappers.<SysDepartment>lambdaQuery().eq(SysDepartment::getPid, pid).orderByAsc(SysDepartment::getDeptSort));
        }
    }

}
