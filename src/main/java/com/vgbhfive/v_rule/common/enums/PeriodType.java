package com.vgbhfive.v_rule.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 18:27
 */
@Getter
@AllArgsConstructor
public enum PeriodType {

    DAY(1, "日", "day"),
    WEEK(2, "周", "week"),
    MONTH(3, "月", "month"),
    YEAR(4, "年", "year");

    private Integer id;

    private String name;

    private String type;

    public static List<Map<String, Object>> allInstance() {
        List<Map<String, Object>> values = new ArrayList<>();
        for (PeriodType value : PeriodType.values()) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("id", value.getId());
            tmp.put("name", value.getName());
            tmp.put("type", value.getType());
            values.add(tmp);
        }
        return values;
    }

}
