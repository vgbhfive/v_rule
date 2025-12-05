package com.vgbhfive.v_rule.dto.deploy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/29 23:09
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class SceneParams {

    private List<SceneStruct.Line> lineList;

    private List<SceneStruct.Scene> sceneList;

    private List<SceneStruct.Divide> divideList;

    private List<SceneStruct.Strategy> strategyList;

    private List<SceneStruct.ProductInterest> interestList;

    private List<SceneStruct.ProductPeriod> periodList;

    private List<SceneStruct.ProductLimit> limitList;

    private List<SceneStruct.ProductCustom> customList;

    private List<SceneStruct.RuleSet> ruleSetList;

    private List<SceneStruct.Rule> ruleList;

    private List<SceneStruct.DataSource> dataSourceList;

    private List<SceneStruct.DataCategory> dataCategoryList;

}
