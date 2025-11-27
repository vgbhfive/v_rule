package com.vgbhfive.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 18:21
 */
@Getter
@AllArgsConstructor
public enum ValueType {

    FIXED("固定值", "fixed"),
    DATASOURCE("数据源", "dataSource");

    private String name;

    private String type;

}
