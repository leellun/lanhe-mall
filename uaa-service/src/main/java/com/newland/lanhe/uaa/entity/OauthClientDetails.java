package com.newland.lanhe.uaa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 授权client详情
 *
 * @author leellun
 * @since 2023-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "OauthClientDetails对象", description = "")
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户端标识")
    @TableId(value = "client_id", type = IdType.AUTO)
    private String clientId;

    @ApiModelProperty(value = "接入资源列表")
    private String resourceIds;

    @ApiModelProperty(value = "客户端秘钥")
    private String clientSecret;

    @ApiModelProperty(value = "授权范围")
    private String scope;

    @ApiModelProperty(value = "允许授权类型")
    private String authorizedGrantTypes;

    @ApiModelProperty(value = "客户端的重定向URI")
    private String webServerRedirectUri;

    @ApiModelProperty(value = "客户端所拥有的权限值")
    private String authorities;

    @ApiModelProperty(value = "设定客户端的access_token的有效时间值(单位:秒)")
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "设定客户端的refresh_token的有效时间值(单位:秒")
    private Integer refreshTokenValidity;

    @ApiModelProperty(value = "这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据,")
    private String additionalInformation;

    private LocalDateTime createTime;

    @ApiModelProperty(value = "用于标识客户端是否已存档(即实现逻辑删除),默认值为’0’(即未存档)")
    private Integer archived;

    @ApiModelProperty(value = "设置客户端是否为受信任的,默认为’0’(即不受信任的,1为受信任的)")
    private Integer trusted;

    @ApiModelProperty(value = "设置用户是否自动Approval操作, 默认值为 ‘false’, 可选值包括 ‘true’,‘false’, ‘read’,‘write’. ")
    private String autoapprove;


}
