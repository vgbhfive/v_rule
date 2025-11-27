package com.vgbhfive.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

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

    public static Map<String, String> allInstance() {
        Map<String, String> values = new HashMap<>();
        for (RuleType value : RuleType.values()) {
            values.put(value.getName(), value.getKey());
        }
        return values;
    }

}
