package com.newland.lanhe.system.model.dto;

import com.newland.lanhe.validator.IntOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 用户中心修改
 * </p>
 *
 */
@Data
public class UserCenterDTO implements Serializable {
    @NotNull(message = "id不能为空")
    private Long id;
    @ApiModelProperty(value = "昵称", required = true)
    @NotBlank(message = "昵称不能为空")
    private String nickName;
    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "手机号不能为空")
    private String phone;
    @ApiModelProperty(value = "性别", required = true)
    @NotBlank(message = "性别不能为空")
    @IntOptions(options = {1,0},message = "性别参数不正确")
    private Integer gender;
}
