package com.newland.lanhe.user.model.dto;

import com.newland.mybatis.page.PageEntity;
import lombok.Data;

import java.util.List;

/**
 * 部门查询
 */
@Data
public class DeptQueryDTO extends PageEntity {

    private String name;

    private Integer enabled;
}
