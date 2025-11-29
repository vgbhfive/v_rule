package com.vgbhfive.v_rule.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 17:32
 */
@Getter
@AllArgsConstructor
public enum StrategyModel {

    SERIAL(1, "串行", "serial"),
    parallel(2, "并行", "parallel");

    private Integer id;

    private String name;

    private String model;

    public static List<Map<String, Object>> allInstance() {
        List<Map<String, Object>> values = new ArrayList<>();
        for (StrategyModel value : StrategyModel.values()) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("id", value.getId());
            tmp.put("name", value.getName());
            tmp.put("model", value.getModel());
            values.add(tmp);
        }
        return values;
    }

}
