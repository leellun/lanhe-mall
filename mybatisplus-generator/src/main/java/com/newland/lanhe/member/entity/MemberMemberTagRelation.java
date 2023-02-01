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
 * 用户和标签关系表
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_member_member_tag_relation")
@ApiModel(value="MemberMemberTagRelation对象", description="用户和标签关系表")
public class MemberMemberTagRelation implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long memberId;

    private Long tagId;


}
