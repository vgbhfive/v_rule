package com.vgbhfive.dto.strategy;

import com.vgbhfive.entity.StrategyRuleDetailEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 18:08
 */
@Data
public class StrategyDto {

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

    private List<StrategyRuleDetailEntity> ruleDetailEntityList;

}
