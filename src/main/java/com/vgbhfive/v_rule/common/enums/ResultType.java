package com.vgbhfive.v_rule.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/30 21:22
 */
@Getter
@AllArgsConstructor
public enum ResultType {

    ACCEPT(1, "accept", "accept"),
    REVIEW(2, "review", "review"),
    REJECT(3, "reject", "reject");

    private Integer id;

    private String name;

    private String result;

    public static List<Map<String, Object>> allInstance() {
        List<Map<String, Object>> values = new ArrayList<>();
        for (ResultType value : ResultType.values()) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("id", value.getId());
            tmp.put("name", value.getName());
            tmp.put("type", value.getResult());
            values.add(tmp);
        }
        return values;
    }


}
