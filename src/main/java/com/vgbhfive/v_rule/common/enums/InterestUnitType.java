package com.vgbhfive.v_rule.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 18:22
 */
@Getter
@AllArgsConstructor
public enum InterestUnitType {

    HUNDRED(1, "百分", "%"),
    THOUSANDTHS(2, "千分", "‰"),
    TEN_THOUSANDTHS(3, "万分", "‱");

    private Integer id;

    private String name;

    private String type;

    public static List<Map<String, Object>> allInstance() {
        List<Map<String, Object>> values = new ArrayList<>();
        for (InterestUnitType value : InterestUnitType.values()) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("id", value.getId());
            tmp.put("name", value.getName());
            tmp.put("type", value.getType());
            values.add(tmp);
        }
        return values;
    }

}
