package com.vgbhfive.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 18:27
 */
@Getter
@AllArgsConstructor
public enum PeriodType {

    DAY("日", "day"),
    WEEK("周", "week"),
    MONTH("月", "month"),
    YEAR("年", "year");

    private String name;

    private String type;

    public static Map<String, String> allInstance() {
        Map<String, String> values = new HashMap<>();
        for (PeriodType value : PeriodType.values()) {
            values.put(value.getName(), value.getType());
        }
        return values;
    }

}
