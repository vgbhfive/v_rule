package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.strategy.StrategyQueryParam;
import com.vgbhfive.v_rule.entity.StrategyEntity;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 14:44
 */
public interface StrategyService {

    ResponseContent queryList(StrategyQueryParam param);

    ResponseContent create(StrategyEntity strategyEntity, boolean isUpdate);

    ResponseContent detail(Integer id);

    ResponseContent update(StrategyEntity strategyEntity);

}
