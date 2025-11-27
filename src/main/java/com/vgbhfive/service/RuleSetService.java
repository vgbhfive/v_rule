package com.vgbhfive.service;

import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.ruleSet.RuleSetQueryParam;
import com.vgbhfive.entity.RuleSetEntity;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 16:42
 */
public interface RuleSetService {

    ResponseContent queryList(RuleSetQueryParam param);

    ResponseContent create(RuleSetEntity ruleSetEntity, boolean isUpdate);

    ResponseContent update(RuleSetEntity ruleSetEntity);

}
