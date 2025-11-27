package com.vgbhfive.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 17:46
 */
@Getter
@AllArgsConstructor
public enum ProductType {

    INTEREST("利率", "interest"),
    LIMIT("额度", "limit"),
    DYNAMIC_LIMIT("动态额度", "dynamic_limit"),
    PERIOD("账期", "period"),
    DYNAMIC_PERIOD("动态账期", "dynamic_period"),
    CUSTOM("自定义", "custom");

    private String name;

    private String type;

    public static Map<String, String> allInstance() {
        Map<String, String> values = new HashMap<>();
        for (ProductType value : ProductType.values()) {
            values.put(value.getName(), value.getType());
        }
        return values;
    }

}
