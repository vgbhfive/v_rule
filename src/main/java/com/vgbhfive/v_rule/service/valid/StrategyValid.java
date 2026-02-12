package com.vgbhfive.v_rule.service.valid;

import com.vgbhfive.v_rule.common.aop.Valid;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.entity.StrategyEntity;
import org.springframework.stereotype.Service;

/**
 * @Author vgbhfive
 * @Date 2026/2/6 12:14
 */
@Service
@Valid(type = ElementTypes.STRATEGY)
public class StrategyValid implements ValidService<StrategyEntity> {

    @Override
    public void checkParams(StrategyEntity strategy, boolean isUpdate) {

    }

}
