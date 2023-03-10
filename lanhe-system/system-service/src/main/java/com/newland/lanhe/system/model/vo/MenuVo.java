package com.newland.lanhe.system.model.vo;

import com.newland.lanhe.system.entity.SysMenu;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 构建前端路由时用到
 */
@ApiModel(value = "前端路由")
@Data
public class MenuVo extends SysMenu {
    private String parentName;
}
