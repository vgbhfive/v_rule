package com.vgbhfive.v_rule.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author vgbhfive
 * @Date 2026/1/30 12:16
 */
@Getter
@AllArgsConstructor
public enum CombineType {

    AND(0, "AND", "AND"),
    OR(1, "OR", "OR");

    private Integer id;

    private String name;

    private String value;

    public static List<Map<String, Object>> allInstance() {
        List<Map<String, Object>> values = new ArrayList<>();
        for (CombineType value : CombineType.values()) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("id", value.getId());
            tmp.put("name", value.getName());
            tmp.put("value", value.getValue());
            values.add(tmp);
        }
        return values;
    }

}
