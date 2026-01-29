package com.vgbhfive.v_rule.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author vgbhfive
 * @Date 2026/1/29 17:35
 */
@Getter
@AllArgsConstructor
public enum ConditionType {

    LESS(0, "小于", "<"),
    LESS_EQUAL(1, "小于等于", "<="),
    EQUAL(2, "等于", "="),
    GREAT(3, "大于", ">"),
    GREAT_EQUAL(4, "大于等于", ">="),
    CONTAIN(5, "包含", "in"),
    NOT_CONTAIN(6, "不包含", "not in"),;

    private Integer id;

    private String name;

    private String value;

    public static List<Map<String, Object>> allInstance() {
        List<Map<String, Object>> values = new ArrayList<>();
        for (ConditionType value : ConditionType.values()) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("id", value.getId());
            tmp.put("name", value.getName());
            tmp.put("value", value.getValue());
            values.add(tmp);
        }
        return values;
    }

}
