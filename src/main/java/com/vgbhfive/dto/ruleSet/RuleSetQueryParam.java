package com.vgbhfive.dto.ruleSet;

import com.vgbhfive.dto.PageRequest;
import lombok.Data;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 16:43
 */
@Data
public class RuleSetQueryParam extends PageRequest {

    private String lineNo;

    private String ruleSetName;

    private String ruleSetNo;

    private Integer isValid;

}
