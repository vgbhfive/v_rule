package com.vgbhfive.dto.strategy;

import lombok.Data;

import java.util.Date;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 14:43
 */
@Data
public class StrategyListDto {

    private Integer id;

    private String lineNo;

    private String sceneNo;

    private String strategyName;

    private String strategyNo;

    private String model;

    private Integer version;

    private Date deployAt;

    private Integer isValid;

    private Integer isDelete;

}
