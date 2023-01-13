package com.newland.lanhe.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础model
 * Author: leell
 * Date: 2023/1/13 23:52:20
 */
@Data
public class BaseEntity implements Serializable {
    @ApiModelProperty(value = "创建者")
    private Long createdBy;

    @ApiModelProperty(value = "修改者")
    private Long updatedBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime gmtModified;
}
