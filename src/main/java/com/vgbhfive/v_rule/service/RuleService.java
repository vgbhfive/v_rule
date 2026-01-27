package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.deploy.VersionDiffDetail;
import com.vgbhfive.v_rule.dto.rule.RuleQueryParam;
import com.vgbhfive.v_rule.entity.RuleEntity;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 16:01
 */
public interface RuleService {

    ResponseContent queryList(RuleQueryParam param);

    ResponseContent create(RuleEntity ruleEntity, boolean isUpdate);

    ResponseContent update(RuleEntity ruleEntity);

    List<SceneStruct.Rule> queryRuleByRuleNos(Set<String> ruleNoSet);

    List<VersionDiffDetail> queryDeployDiff(List<SceneStruct.Rule> ruleList, List<SceneStruct.Rule> lastRuleList) throws Exception;

    ResponseContent updateRuleDeployTime(List<SceneStruct.Rule> ruleList, Date deployTime);

    ResponseContent dropdownList();

}
