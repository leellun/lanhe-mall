package com.newland.lanhe.user.model.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户查询
 * Author: leell
 * Date: 2023/1/10 13:27:40
 */
@Data
public class UserQueryDTO {
    private Long id;

    private Set<Long> deptIds = new HashSet<>();

    private String blurry;

    private Boolean enabled;

    private List<String> gmtCreate;

    private Long deptId;
}
