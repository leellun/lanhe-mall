package com.newland.lanhe.cms.model.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SubjectProductRelationDto implements Serializable {

    private static final long serialVersionUID = -4079484815593829058L;
    private Long id;

    private Long subjectId;

    private Long productId;


}
