package com.newland.lanhe.product.model.dto;

import com.newland.lanhe.cms.model.dto.PrefrenceAreaProductRelationDto;
import com.newland.lanhe.cms.model.dto.SubjectProductRelationDto;
import com.newland.lanhe.product.entity.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品提交数据
 * Author: leell
 * Date: 2023/2/2 17:01:15
 */
@Data
public class ProductDto extends Product {
    @ApiModelProperty("商品阶梯价格设置")
    private List<ProductLadder> productLadderList;
    @ApiModelProperty("商品满减价格设置")
    private List<ProductFullReduction> productFullReductionList;
    @ApiModelProperty("商品会员价格设置")
    private List<MemberPrice> memberPriceList;
    @ApiModelProperty("商品的sku库存信息")
    private List<SkuStock> skuStockList;
    @ApiModelProperty("商品属性图片关联")
    private List<ProductAttrPic> productAttrPics;
    @ApiModelProperty("商品参数及自定义规格属性")
    private List<ProductAttributeValue> productAttributeValueList;
    @ApiModelProperty("专题和商品关系")
    private List<SubjectProductRelationDto> subjectProductRelationList;
    @ApiModelProperty("优选专区和商品的关系")
    private List<PrefrenceAreaProductRelationDto> prefrenceAreaProductRelationList;

}