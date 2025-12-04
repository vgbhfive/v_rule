package com.vgbhfive.v_rule.dto.product;

import lombok.Data;

import java.util.Date;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 11:41
 */
@Data
public class ProductLimitListDto {

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

    private Date createAt;

}
