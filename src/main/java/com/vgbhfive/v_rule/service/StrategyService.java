package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.deploy.VersionDiffDetail;
import com.vgbhfive.v_rule.dto.strategy.StrategyQueryParam;
import com.vgbhfive.v_rule.entity.StrategyEntity;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 14:44
 */
public interface StrategyService {

    ResponseContent queryList(StrategyQueryParam param);

    ResponseContent create(StrategyEntity strategyEntity, boolean isUpdate);

    ResponseContent detail(Integer id);

    ResponseContent update(StrategyEntity strategyEntity);

    List<SceneStruct.Strategy> queryStrategyByStrategyNos(Set<String> strategyNoSet, String type);

    List<VersionDiffDetail> queryDeployDiff(List<SceneStruct.Strategy> strategyList, List<SceneStruct.Strategy> lastStrategyList) throws Exception;

    ResponseContent updateStrategyDeployTime(List<SceneStruct.Strategy> strategyList, Date deployTime);

    ResponseContent dropdownList();

}
