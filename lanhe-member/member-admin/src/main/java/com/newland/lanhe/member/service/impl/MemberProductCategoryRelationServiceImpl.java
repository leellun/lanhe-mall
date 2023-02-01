package com.newland.lanhe.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.member.entity.MemberProductCategoryRelation;
import com.newland.lanhe.member.mapper.MemberProductCategoryRelationMapper;
import com.newland.lanhe.member.service.MemberProductCategoryRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员与产品分类关系表（用户喜欢的分类） 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@Service
public class MemberProductCategoryRelationServiceImpl extends ServiceImpl<MemberProductCategoryRelationMapper, MemberProductCategoryRelation> implements MemberProductCategoryRelationService {

}
