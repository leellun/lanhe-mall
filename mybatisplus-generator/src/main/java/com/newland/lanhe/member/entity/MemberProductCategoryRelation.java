package com.newland.lanhe.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 会员与产品分类关系表（用户喜欢的分类）
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_member_product_category_relation")
@ApiModel(value="MemberProductCategoryRelation对象", description="会员与产品分类关系表（用户喜欢的分类）")
public class MemberProductCategoryRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "会员与产品分类关系id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "会员id")
    private Long memberId;

    @ApiModelProperty(value = "产品分类id")
    private Long productCategoryId;


}
