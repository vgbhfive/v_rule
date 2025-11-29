package com.vgbhfive.v_rule.dto.datasource;

import lombok.Data;

/**
 * @Author vgbhfive
 * @Date 2025/11/26 15:41
 */
@Data
public class DataSourceListDto {

    private Integer id;

    private String lineNo;

    private String dataSourceName;

    private String dataSourceNo;

    private String dataCategoryNo;

    private String sourceFrom;

    private String sourceType;

    private String field;

    private String type;

    private String format;

    private Integer version;

    private Integer isValid;

    private Integer isDelete;

}
