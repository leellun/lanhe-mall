package com.newland.lanhe.user.model.dto;

import com.newland.mybatis.page.PageEntity;
import lombok.Data;

import java.util.List;

/**
 * job查询
 * Author: leell
 * Date: 2023/1/10 12:58:01
 */
@Data
public class JobQueryDTO extends PageEntity {
    private String name;

    private Boolean enabled;

    private List<String> gmtCreate;
}
