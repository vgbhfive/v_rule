package com.vgbhfive.v_rule.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author vgbhfive
 * @Date 2026/2/12 16:59
 */
@Getter
@AllArgsConstructor
public enum ElementTypes {

    LINE("line"),
    SCENE("scene"),
    DIVIDE("divide"),
    STRATEGY("strategy"),
    PRODUCT("product"),
    RULE_SET("ruleSet"),
    RULE("rule"),
    DATA_CATEGORY("dataCategory"),
    DATA_SOURCE("dataSource"),
    ;

    private String name;

}
