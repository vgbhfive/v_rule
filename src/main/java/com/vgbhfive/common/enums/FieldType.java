package com.vgbhfive.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/28 0:03
 */
@Getter
@AllArgsConstructor
public enum FieldType {

    NUMBER(1, "数值", "number"),
    STRING(2, "字符串", "string"),
    LIST(3, "列表", "list"),
    DATE(4, "时间", "date"),
    JSON(4, "JSON", "json");

    private Integer id;

    private String name;

    private String type;

    public static List<Map<String, Object>> allInstance() {
        List<Map<String, Object>> values = new ArrayList<>();
        for (FieldType value : FieldType.values()) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("id", value.getId());
            tmp.put("name", value.getName());
            tmp.put("type", value.getType());
            values.add(tmp);
        }
        return values;
    }

}
