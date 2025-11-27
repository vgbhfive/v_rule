package com.vgbhfive.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

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

}
