package com.newland.lanhe.user.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.impl.IndexedStringListSerializer;
import com.newland.lanhe.user.entity.SysUser;
import com.newland.lanhe.validator.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户添加、修改
 * Author: leell
 * Date: 2022/12/7 14:16:15
 */
@Data
@ApiModel(value = "用户列表返回对象")
public class SysUserDto extends SysUser {
    @ApiModelProperty(value = "ID")
    @NotNull(message = "id不能为空",groups = {Update.class})
    private Long id;

    @ApiModelProperty(value = "角色id")
    @JsonSerialize(using= IndexedStringListSerializer.class)
    private List<Long> roleIds;
}
