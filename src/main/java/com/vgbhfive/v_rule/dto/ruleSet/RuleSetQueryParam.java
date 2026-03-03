package com.vgbhfive.v_rule.dto.ruleSet;

import com.vgbhfive.v_rule.dto.PageRequest;
import lombok.Data;

import java.util.List;

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

    private List<String> lineNoList;

}
