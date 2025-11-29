package com.vgbhfive.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/29 23:04
 */
@Getter
@AllArgsConstructor
public enum DeployStatusCode {

    APPROVAL(1, "提交", 1),
    PASS(2, "通过", 2),
    REJECT(3, "拒绝", 3),
    CANCEL(4, "取消", 4);

    private Integer id;

    private String name;

    private Integer code;

    public static List<Map<String, Object>> allInstance() {
        List<Map<String, Object>> values = new ArrayList<>();
        for (DeployStatusCode value : DeployStatusCode.values()) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("id", value.getId());
            tmp.put("name", value.getName());
            tmp.put("code", value.getCode());
            values.add(tmp);
        }
        return values;
    }


}
