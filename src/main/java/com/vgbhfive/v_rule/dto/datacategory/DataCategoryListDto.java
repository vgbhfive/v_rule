package com.vgbhfive.v_rule.dto.datacategory;

import lombok.Data;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/26 21:36
 */
@Data
public class DataCategoryListDto {

    private Integer id;

    private String lineNo;

    private String dataCategoryName;

    private String dataCategoryNo;

    private String sourceFrom;

    private String sourceType;

    private Integer categoryType;

    private Integer priority;

    private Integer version;

    private Integer isValid;

    private Integer isDelete;

}
