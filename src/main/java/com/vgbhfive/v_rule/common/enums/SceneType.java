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
 * @date: 2025/11/29 10:55
 */
@Getter
@AllArgsConstructor
public enum SceneType {

    SCENE(1, "场景", "scene");

    private Integer id;

    private String name;

    private String type;

    public static List<Map<String, Object>> allInstance() {
        List<Map<String, Object>> values = new ArrayList<>();
        for (SceneType value : SceneType.values()) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("id", value.getId());
            tmp.put("name", value.getName());
            tmp.put("type", value.getType());
            values.add(tmp);
        }
        return values;
    }

}
