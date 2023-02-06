package com.newland.lanhe.cms.entity;

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
 * 优选专区
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cms_prefrence_area")
@ApiModel(value="PrefrenceArea对象", description="优选专区")
public class PrefrenceArea implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "优选专区id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "优选专区名称")
    private String name;

    @ApiModelProperty(value = "子标题")
    private String subTitle;

    @ApiModelProperty(value = "展示图片")
    private byte[] pic;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "显示状态")
    private Integer showStatus;


}
