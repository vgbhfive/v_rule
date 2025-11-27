package com.vgbhfive.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/28 0:03
 */
@Getter
@AllArgsConstructor
public enum FieldType {

    NUMBER("数值", "number"),
    STRING("字符串", "string"),
    LIST("列表", "list"),
    DATE("时间", "date"),
    JSON("JSON", "json");

    private String name;

    private String type;

    public static Map<String, String> allInstance() {
        Map<String, String> values = new HashMap<>();
        for (FieldType value : FieldType.values()) {
            values.put(value.getName(), value.getType());
        }
        return values;
    }

}
