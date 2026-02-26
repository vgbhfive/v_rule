package com.vgbhfive.v_rule.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author vgbhfive
 * @Date 2026/1/28 17:36
 */
@Getter
@AllArgsConstructor
public enum CategoryType {

    LOCAL(1, "请求", "local", 0),
    PYTHON(2, "Python", "python", 1),
    HTTP(3, "HTTP", "http", 2),
    ;

    private Integer id;

    private String name;

    private String key;

    private Integer type;

    public static List<Map<String, Object>> allInstance() {
        List<Map<String, Object>> values = new ArrayList<>();
        for (CategoryType value : CategoryType.values()) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("id", value.getId());
            tmp.put("name", value.getName());
            tmp.put("type", value.getType());
            values.add(tmp);
        }
        return values;
    }

}
