package com.newland.lanhe.cms.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PrefrenceAreaProductRelationDto implements Serializable {
    private static final long serialVersionUID = -8635290899991174718L;
    private Long id;

    private Long prefrenceAreaId;

    private Long productId;
}
