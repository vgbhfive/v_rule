package com.vgbhfive.v_rule.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 17:34
 */
@Getter
@AllArgsConstructor
public enum RuleType {

    RULE(1, "规则", "rule"),
    RULE_SET(2, "规则集", "rule_set");

    private Integer id;

    private String name;

    private String key;

    public static List<Map<String, Object>> allInstance() {
        List<Map<String, Object>> values = new ArrayList<>();
        for (RuleType value : RuleType.values()) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("id", value.getId());
            tmp.put("name", value.getName());
            tmp.put("key", value.getKey());
            values.add(tmp);
        }
        return values;
    }

}
