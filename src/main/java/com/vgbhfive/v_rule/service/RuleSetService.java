package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.deploy.VersionDiffDetail;
import com.vgbhfive.v_rule.dto.ruleSet.RuleSetQueryParam;
import com.vgbhfive.v_rule.entity.RuleSetEntity;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 16:42
 */
public interface RuleSetService {

    ResponseContent queryList(RuleSetQueryParam param);

    ResponseContent create(RuleSetEntity ruleSetEntity, boolean isUpdate);

    ResponseContent update(RuleSetEntity ruleSetEntity);

    List<SceneStruct.RuleSet> queryRuleSetByRuleSetNos(Set<String> ruleSetNoSet);

    List<VersionDiffDetail> queryDeployDiff(List<SceneStruct.RuleSet> ruleSetList, List<SceneStruct.RuleSet> lastRuleSetList) throws Exception;

    ResponseContent updateRuleSetDeployTime(List<SceneStruct.RuleSet> ruleSetList, Date deployTime);

    ResponseContent dropdownList();

}
