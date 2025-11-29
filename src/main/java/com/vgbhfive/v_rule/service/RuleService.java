package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.rule.RuleQueryParam;
import com.vgbhfive.v_rule.entity.RuleEntity;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 16:01
 */
public interface RuleService {

    ResponseContent queryList(RuleQueryParam param);

    ResponseContent create(RuleEntity ruleEntity, boolean isUpdate);

    ResponseContent update(RuleEntity ruleEntity);

}
