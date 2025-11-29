package com.vgbhfive.v_rule.dto.datacategory;

import com.vgbhfive.v_rule.dto.PageRequest;
import lombok.Data;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/26 21:32
 */
@Data
public class DataCategoryQueryParam extends PageRequest {

    private String lineNo;

    private String dataCategoryName;

    private String dataCategoryNo;

    private Integer isValid;

}
