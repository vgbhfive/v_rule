package com.vgbhfive.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 18:21
 */
@Getter
@AllArgsConstructor
public enum ValueType {

    FIXED("固定值", "fixed"),
    DATASOURCE("数据源", "dataSource");

    private String name;

    private String type;

    public static Map<String, String> allInstance() {
        Map<String, String> values = new HashMap<>();
        for (ValueType value : ValueType.values()) {
            values.put(value.getName(), value.getType());
        }
        return values;
    }

}
