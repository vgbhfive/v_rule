package com.vgbhfive.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 17:32
 */
@Getter
@AllArgsConstructor
public enum StrategyModel {

    SERIAL("串行", "serial"),
    parallel("并行", "parallel");

    private String name;

    private String model;

    public static Map<String, String> allInstance() {
        Map<String, String> values = new HashMap<>();
        for (StrategyModel value : StrategyModel.values()) {
            values.put(value.getName(), value.getModel());
        }
        return values;
    }

}
