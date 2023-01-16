package com.newland.lanhe.uua.entity;

import java.time.LocalDateTime;
import java.sql.Blob;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author leellun
 * @since 2023-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="OauthCode对象", description="")
public class OauthCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据的创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "存储服务端系统生成的code的值(未加密)")
    private String code;

    @ApiModelProperty(value = "存储将AuthorizationRequestHolder.java对象序列化后的二进制数据.")
    private Blob authentication;


}
