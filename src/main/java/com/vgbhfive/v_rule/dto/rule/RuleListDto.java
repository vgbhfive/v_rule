package com.vgbhfive.v_rule.dto.rule;

import lombok.Data;

import java.util.Date;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 16:04
 */
@Data
public class RuleListDto {

    private Integer id;

    private String lineNo;

    private String ruleName;

    private String ruleNo;

    private String dataSourceNo;

    private String cond;

    private String threshold;

    private String thresholdType;

    private String result;

    private Integer version;

    private Date deployAt;

    private Integer isValid;

    private Integer isDelete;

}
