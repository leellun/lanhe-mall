package com.newland.lanhe.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 专题表
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cms_subject")
@ApiModel(value="Subject对象", description="专题表")
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "专题id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "分类id")
    private Long categoryId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "专题主图")
    private String pic;

    @ApiModelProperty(value = "关联产品数量")
    private Integer productCount;

    @ApiModelProperty(value = "推荐状态")
    private Integer recommendStatus;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "收藏数")
    private Integer collectCount;

    @ApiModelProperty(value = "阅读数")
    private Integer readCount;

    @ApiModelProperty(value = "评论数")
    private Integer commentCount;

    @ApiModelProperty(value = "画册图片用逗号分割")
    private String albumPics;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "显示状态：0->不显示；1->显示")
    private Integer showStatus;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "转发数")
    private Integer forwardCount;

    @ApiModelProperty(value = "专题分类名称")
    private String categoryName;


}
