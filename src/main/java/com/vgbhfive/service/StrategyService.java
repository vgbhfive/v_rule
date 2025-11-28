package com.vgbhfive.service;

import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.strategy.StrategyQueryParam;
import com.vgbhfive.entity.StrategyEntity;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 14:44
 */
public interface StrategyService {

    ResponseContent queryList(StrategyQueryParam param);

    ResponseContent create(StrategyEntity strategyEntity, boolean isUpdate);

    ResponseContent update(StrategyEntity strategyEntity);

}
