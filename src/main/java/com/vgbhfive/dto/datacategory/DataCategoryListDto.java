package com.vgbhfive.dto.datacategory;

import lombok.Data;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/26 21:36
 */
@Data
public class DataCategoryListDto {

    private Integer id;

    private String dataCategoryName;

    private String dataCategoryNo;

    private String sourceFrom;

    private String sourceType;

    private Integer categoryType;

    private Integer priority;

    private Integer version;

    private Integer isValid;

}
