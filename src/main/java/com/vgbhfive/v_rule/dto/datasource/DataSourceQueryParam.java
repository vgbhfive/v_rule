package com.vgbhfive.v_rule.dto.datasource;

import com.vgbhfive.v_rule.dto.PageRequest;
import lombok.Data;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/11/26 12:15
 */
@Data
public class DataSourceQueryParam extends PageRequest {

    private String lineNo;

    private String dataSourceName;

    private String dataSourceNo;

    private String dataCategoryNo;

    private String field;

    private Integer isValid;

    private List<String> lineNoList;

}
