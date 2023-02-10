package com.newland.lanhe.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author leellun
 * @since 2023-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="BackLog对象", description="")
public class BackLog implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "编码")
    private Integer code;

    @ApiModelProperty(value = "数据")
    private String data;

    @ApiModelProperty(value = "创建者")
    private String createdBy;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;


}
