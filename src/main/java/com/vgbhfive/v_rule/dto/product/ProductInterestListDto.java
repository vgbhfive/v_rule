package com.vgbhfive.v_rule.dto.product;

import lombok.Data;

import java.util.Date;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/27 23:08
 */
@Data
public class ProductInterestListDto {

    private Integer id;

    private String lineNo;

    private String productName;

    private String productNo;

    private String remark;

    private String version;

    private String isValid;

    private String isDelete;

    private String valueType;

    private String value;

    private String unit;

    private Date createAt;

}
