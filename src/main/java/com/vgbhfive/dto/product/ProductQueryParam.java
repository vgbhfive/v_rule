package com.vgbhfive.dto.product;

import com.vgbhfive.dto.PageRequest;
import lombok.Data;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/27 23:05
 */
@Data
public class ProductQueryParam extends PageRequest {

    private String lineNo;

    private String type;

    private String productName;

    private String productNo;

}
