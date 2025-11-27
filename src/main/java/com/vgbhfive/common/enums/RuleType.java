package com.vgbhfive.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 17:34
 */
@Getter
@AllArgsConstructor
public enum RuleType {

    RULE("规则", "rule"),
    RULE_SET("规则集", "rule_set");

    private String name;

    private String key;

}
