package com.vgbhfive.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

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

}
