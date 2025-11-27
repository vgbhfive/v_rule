package com.vgbhfive.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 18:22
 */
@Getter
@AllArgsConstructor
public enum InterestUnitType {

    HUNDRED("百分位", "%"),
    THOUSANDTHS("千分位", "‰"),
    TEN_THOUSANDTHS("万分位", "‱");

    private String name;

    private String type;

    public static Map<String, String> allInstance() {
        Map<String, String> values = new HashMap<>();
        for (InterestUnitType value : InterestUnitType.values()) {
            values.put(value.getName(), value.getType());
        }
        return values;
    }

}
