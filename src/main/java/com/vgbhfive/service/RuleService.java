package com.vgbhfive.service;

import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.rule.RuleQueryParam;
import com.vgbhfive.entity.RuleEntity;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 16:01
 */
public interface RuleService {

    ResponseContent queryList(RuleQueryParam param);

    ResponseContent create(RuleEntity ruleEntity, boolean isUpdate);

    ResponseContent update(RuleEntity ruleEntity);

}
