package com.vgbhfive.v_rule.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author vgbhfive
 * @Date 2025/12/3 10:23
 */
@Getter
@AllArgsConstructor
public enum UserStatus {

    NORMAL(0, "正常"),
    FROZEN(1, "冻结"),
    DEFAULT_PASSWORD(2, "默认密码");

    private Integer code;

    private String remark;

}
