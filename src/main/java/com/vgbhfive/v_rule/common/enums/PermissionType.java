package com.vgbhfive.v_rule.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author vgbhfive
 * @Date 2026/3/3 17:37
 */
@Getter
@AllArgsConstructor
public enum PermissionType {

    PAGE(0, 1, "页面"),
    BUTTON(1, 2, "按钮");

    private Integer id;

    private Integer type;

    private String name;

}
