package com.vgbhfive.v_rule.dto.rule;

import com.vgbhfive.v_rule.dto.PageRequest;
import lombok.Data;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 16:01
 */
@Data
public class RuleQueryParam extends PageRequest {

    private String lineNo;

    private String ruleName;

    private String ruleNo;

    private String dataSourceNo;

    private Integer isValid;

}
