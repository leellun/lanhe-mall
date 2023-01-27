package com.newland.lanhe.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.user.entity.SysJob;
import com.newland.lanhe.user.enums.UserServiceErrorEnum;
import com.newland.lanhe.user.mapper.SysJobMapper;
import com.newland.lanhe.user.model.dto.JobQueryDTO;
import com.newland.lanhe.user.service.SysJobService;
import com.newland.lanhe.utils.AssertUtil;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 岗位 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
@Service
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements SysJobService {

    @Override
    public List<SysJob> getAllJobs() {
        return baseMapper.selectList(Wrappers.<SysJob>lambdaQuery().orderByAsc(SysJob::getJobSort));
    }

    @Override
    public Page<SysJob> getJobs(JobQueryDTO jobQueryDTO) {
        Page<SysJob> page = PageWrapper.wrapper(jobQueryDTO);
        return baseMapper.selectPage(page, Wrappers.lambdaQuery());
    }

    @Override
    public void addJob(SysJob sysJob) {
        SysJob dbJob = baseMapper.selectOne(Wrappers.<SysJob>lambdaQuery().eq(SysJob::getName, sysJob.getName()));
        AssertUtil.isNull(dbJob, UserServiceErrorEnum.JOB_EXIST);
        baseMapper.insert(sysJob);
    }

    @Override
    public void updateJob(SysJob sysJob) {
        SysJob dbJob = baseMapper.selectById(sysJob);
        AssertUtil.isNull(dbJob, UserServiceErrorEnum.JOB_NOT_EXIST);
    }

    @Override
    public void deleteJob(Set<Long> ids) {
        int count = baseMapper.deleteBatchIds(ids);
        AssertUtil.isTrue(count > 0, UserServiceErrorEnum.JOB_DELETE_FAIL);
    }
}
