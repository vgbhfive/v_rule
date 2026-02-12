package com.vgbhfive.v_rule.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

/**
 * @Author vgbhfive
 * @Date 2026/1/29 17:35
 */
@Getter
@AllArgsConstructor
public enum ConditionType {

    LESS(1, "小于", "<"),
    LESS_EQUAL(2, "小于等于", "<="),
    EQUAL(3, "等于", "="),
    GREAT(4, "大于", ">"),
    GREAT_EQUAL(5, "大于等于", ">="),
    NOT_EQUAL(6, "不等于","!="),
    BETWEEN(7, "范围","between"),
    IN(8, "in","in"),
    NOT_IN(9, "not in","not in"),
    CONTAIN(10, "包含", "contain"),
    NOT_CONTAIN(11, "不包含", "not contain"),
    EMPTY(12, "为空", "empty"),
    NOT_EMPTY(13, "不为空","not empty"),
    DRY_RUN(14, "空跑", "dry run"),
//    LABEL(15, "标签", "label"),
//    ASSIGN(16, "赋值", "assign"),
//    BLACK(17, "拉黑", "black"),
//    WHITE(18, "加白", "white"),
    ;

    private Integer id;

    private String name;

    private String value;

    public static List<Map<String, Object>> allInstance() {
        List<Map<String, Object>> values = new ArrayList<>();
        for (ConditionType value : ConditionType.values()) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("id", value.getId());
            tmp.put("name", value.getName());
            tmp.put("value", value.getValue());
            values.add(tmp);
        }
        return values;
    }

    public static List<Map<String, Object>> allRuleSetInstance() {
        List<Map<String, Object>> values = new ArrayList<>();
        for (ConditionType value : Arrays.asList(ConditionType.EQUAL, ConditionType.NOT_EQUAL,
                ConditionType.IN, ConditionType.NOT_IN, ConditionType.DRY_RUN)) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("id", value.getId());
            tmp.put("name", value.getName());
            tmp.put("value", value.getValue());
            values.add(tmp);
        }
        return values;
    }

}
