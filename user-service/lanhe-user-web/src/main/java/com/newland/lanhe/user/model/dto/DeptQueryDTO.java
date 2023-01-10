package com.newland.lanhe.user.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 部门查询
 */
@Data
public class DeptQueryDTO {

    private String name;

    private Boolean enabled;

    private Long pid;

    private Boolean pidIsNull;

    private List<String> gmtCreate;
}
