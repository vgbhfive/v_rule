package com.vgbhfive.v_rule.dto.product;

import lombok.Data;

import java.util.Date;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 11:27
 */
@Data
public class ProductPeriodListDto {

    private Integer id;

    private String lineNo;

    private String productName;

    private String productNo;

    private String remark;

    private Integer version;

    private Integer isValid;

    private String valueType;

    private String value;

    private String periodType;

    private Date createAt;

}
