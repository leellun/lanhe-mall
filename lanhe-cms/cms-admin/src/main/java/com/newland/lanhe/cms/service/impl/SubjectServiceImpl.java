package com.newland.lanhe.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.cms.entity.Subject;
import com.newland.lanhe.cms.entity.SubjectProductRelation;
import com.newland.lanhe.cms.mapper.SubjectMapper;
import com.newland.lanhe.cms.mapper.SubjectProductRelationMapper;
import com.newland.lanhe.cms.model.dto.SubjectProductRelationDto;
import com.newland.lanhe.cms.service.SubjectProductRelationService;
import com.newland.lanhe.cms.service.SubjectService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 专题表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-02
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private SubjectProductRelationService subjectProductRelationService;
    @Autowired
    private SubjectProductRelationMapper subjectProductRelationMapper;

    @Override
    public List<Subject> listAll() {
        return subjectMapper.selectList(Wrappers.emptyWrapper());
    }

    @Override
    public Page<Subject> list(String keyword, Integer pageNo, Integer pageSize) {
        Page<Subject> wrapper = PageWrapper.wrapper(PageEntity.page(pageNo, pageSize));
        LambdaQueryWrapper<Subject> queryWrapper = Wrappers.<Subject>lambdaQuery();
        if (org.apache.commons.lang.StringUtils.isNotEmpty(keyword)) {
            queryWrapper.like(Subject::getTitle, keyword);
        }
        return baseMapper.selectPage(wrapper, queryWrapper);
    }

    @Override
    public void relateAndInsertList(List<SubjectProductRelationDto> productRelationInputs, Long productId) {
        List<SubjectProductRelation> productRelationList = relationConvert(productRelationInputs, productId);
        subjectProductRelationService.saveBatch(productRelationList);
    }


    @Override
    public void relateAndUpdateList(List<SubjectProductRelationDto> productRelationInputs, Long productId) {
        List<SubjectProductRelation> productRelationList = relationConvert(productRelationInputs, productId);
        //关联专题
        subjectProductRelationService.remove(Wrappers.<SubjectProductRelation>lambdaQuery().eq(SubjectProductRelation::getProductId, productId));
        subjectProductRelationService.saveBatch(productRelationList);
    }

    @Override
    public List<SubjectProductRelationDto> relationByProductId(Long productId) {
        List<SubjectProductRelation> productRelationList = subjectProductRelationMapper.selectList(Wrappers.<SubjectProductRelation>lambdaQuery().eq(SubjectProductRelation::getProductId, productId));
        List<SubjectProductRelationDto> productRelationInputs = productRelationList.stream().map(p -> {
            SubjectProductRelationDto SubjectProductRelationDto = new SubjectProductRelationDto();
            BeanUtils.copyProperties(p, SubjectProductRelationDto);
            return SubjectProductRelationDto;
        }).collect(Collectors.toList());
        return productRelationInputs;
    }


    private List<SubjectProductRelation> relationConvert(List<SubjectProductRelationDto> productRelationInputs, Long productId) {
        return productRelationInputs.stream().map(p -> {
            SubjectProductRelation SubjectProductRelation = new SubjectProductRelation();
            BeanUtils.copyProperties(p, SubjectProductRelation);
            SubjectProductRelation.setProductId(productId);
            return SubjectProductRelation;
        }).collect(Collectors.toList());
    }
}
