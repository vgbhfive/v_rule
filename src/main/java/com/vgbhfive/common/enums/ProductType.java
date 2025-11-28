package com.vgbhfive.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 17:46
 */
@Getter
@AllArgsConstructor
public enum ProductType {

    INTEREST(1, "利率", "interest"),
    LIMIT(2, "额度", "limit"),
    DYNAMIC_LIMIT(3, "动态额度", "dynamic_limit"),
    PERIOD(4, "账期", "period"),
    DYNAMIC_PERIOD(5, "动态账期", "dynamic_period"),
    CUSTOM(6, "自定义", "custom");

    private Integer id;

    private String name;

    private String type;

    public static List<Map<String, Object>> allInstance() {
        List<Map<String, Object>> values = new ArrayList<>();
        for (ProductType value : ProductType.values()) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("id", value.getId());
            tmp.put("name", value.getName());
            tmp.put("type", value.getType());
            values.add(tmp);
        }
        return values;
    }

}
