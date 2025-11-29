package com.vgbhfive.v_rule.dto.product;

import com.vgbhfive.v_rule.dto.PageRequest;
import lombok.Data;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/27 23:05
 */
@Data
public class ProductQueryParam extends PageRequest {

    private String lineNo;

    private String type;

    private String productName;

    private String productNo;

    private Integer isValid;

}
