package com.newland.lanhe.product.mapper;

import com.newland.lanhe.product.entity.ProductFullReduction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 产品满减表(只针对同商品) Mapper 接口
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@Repository
public interface ProductFullReductionMapper extends BaseMapper<ProductFullReduction> {

}
