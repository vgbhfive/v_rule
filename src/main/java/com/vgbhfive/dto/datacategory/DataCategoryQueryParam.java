package com.vgbhfive.dto.datacategory;

import com.vgbhfive.dto.PageRequest;
import lombok.Data;

/**
 * @projectName: v-rule
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
