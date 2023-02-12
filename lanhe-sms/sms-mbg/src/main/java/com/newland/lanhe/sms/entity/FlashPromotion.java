package com.newland.lanhe.sms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 限时购表
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sms_flash_promotion")
@ApiModel(value="FlashPromotion对象", description="限时购表")
public class FlashPromotion implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "限时购id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "限时购标题")
    private String title;

    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始日期")
    private LocalDate startDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期")
    private LocalDate endDate;

    @ApiModelProperty(value = "上下线状态")
    private Integer status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "秒杀时间段名称")
    private LocalDateTime createTime;


}
