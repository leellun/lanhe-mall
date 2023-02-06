package com.newland.lanhe.cms.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.cms.entity.PrefrenceArea;
import com.newland.lanhe.cms.entity.PrefrenceAreaProductRelation;
import com.newland.lanhe.cms.mapper.PrefrenceAreaMapper;
import com.newland.lanhe.cms.mapper.PrefrenceAreaProductRelationMapper;
import com.newland.lanhe.cms.model.dto.PrefrenceAreaProductRelationDto;
import com.newland.lanhe.cms.service.PrefrenceAreaProductRelationService;
import com.newland.lanhe.cms.service.PrefrenceAreaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 优选专区 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-02
 */
@Service
public class PrefrenceAreaServiceImpl extends ServiceImpl<PrefrenceAreaMapper, PrefrenceArea> implements PrefrenceAreaService {

    @Autowired
    private PrefrenceAreaProductRelationService prefrenceAreaProductRelationService;

    @Autowired
    private PrefrenceAreaProductRelationMapper prefrenceAreaProductRelationMapper;


    @Override
    public List<PrefrenceArea> listAll() {
        return baseMapper.selectList(Wrappers.emptyWrapper());
    }

    @Override
    public void relateAndInsertList(List<PrefrenceAreaProductRelationDto> productRelationInput, Long productId) {
        List<PrefrenceAreaProductRelation> productRelationList = relationConvert(productRelationInput, productId);
        prefrenceAreaProductRelationService.saveBatch(productRelationList);
    }


    @Override
    public void relateAndUpdateList(List<PrefrenceAreaProductRelationDto> productRelationInput, Long productId) {
        List<PrefrenceAreaProductRelation> productRelationList = relationConvert(productRelationInput, productId);
        //关联优选
        prefrenceAreaProductRelationService.remove(Wrappers.<PrefrenceAreaProductRelation>lambdaQuery().eq(PrefrenceAreaProductRelation::getProductId, productId));
        prefrenceAreaProductRelationService.saveBatch(productRelationList);
    }

    /**
     * 通过id 查询  优选专区
     *
     * @param productId
     * @return
     */
    @Override
    public List<PrefrenceAreaProductRelationDto> relationByProductId(Long productId) {
        List<PrefrenceAreaProductRelation> productRelationList = prefrenceAreaProductRelationMapper.selectList(Wrappers.<PrefrenceAreaProductRelation>lambdaQuery().eq(PrefrenceAreaProductRelation::getProductId, productId));
        List<PrefrenceAreaProductRelationDto> productRelationInputs = productRelationList.stream().map(p -> {
            PrefrenceAreaProductRelationDto relationInput = new PrefrenceAreaProductRelationDto();
            BeanUtils.copyProperties(p, relationInput);
            return relationInput;
        }).collect(Collectors.toList());
        return productRelationInputs;
    }

    private List<PrefrenceAreaProductRelation> relationConvert(List<PrefrenceAreaProductRelationDto> productRelationInput, Long productId) {
        return productRelationInput.stream().map(p -> {
            PrefrenceAreaProductRelation cmsSubjectProductRelation = new PrefrenceAreaProductRelation();
            BeanUtils.copyProperties(p, cmsSubjectProductRelation);
            cmsSubjectProductRelation.setProductId(productId);
            return cmsSubjectProductRelation;
        }).collect(Collectors.toList());
    }
}
