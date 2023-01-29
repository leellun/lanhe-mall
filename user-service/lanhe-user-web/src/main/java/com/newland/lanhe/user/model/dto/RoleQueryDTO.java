package com.newland.lanhe.user.model.dto;

import com.newland.mybatis.page.PageEntity;
import lombok.Data;

import java.util.List;

/**
 * 查询角色
 * Author: leell
 * Date: 2023/1/10 13:18:41
 */
@Data
public class RoleQueryDTO extends PageEntity {
    private String name;

    private Integer enabled;
}
