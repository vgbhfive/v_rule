package com.vgbhfive.v_rule.service.valid;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vgbhfive.v_rule.common.aop.Valid;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.common.exception.ParamException;
import com.vgbhfive.v_rule.entity.DivideEntity;
import com.vgbhfive.v_rule.entity.StrategyEntity;
import com.vgbhfive.v_rule.mapper.StrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author vgbhfive
 * @Date 2026/2/6 12:14
 */
@Service
@Valid(type = ElementTypes.STRATEGY)
public class StrategyValid implements ValidService<StrategyEntity> {

    @Autowired
    private StrategyMapper strategyMapper;

    @Override
    public void checkParams(StrategyEntity strategyEntity, boolean isUpdate) {
        List<StrategyEntity> objectList = strategyMapper.selectList(new QueryWrapper<StrategyEntity>()
                .eq("strategy_name", strategyEntity.getStrategyName()).eq("line_no", strategyEntity.getLineNo()).eq("is_delete", 0));
        if (isUpdate) {
            for (StrategyEntity entity : objectList) {
                if (!strategyEntity.getId().equals(entity.getId())) {
                    throw new ParamException("同一业务线下不允许名称相同:" + strategyEntity.getStrategyName());
                }
            }
        } else {
            // 新增
            if (Objects.nonNull(objectList) && !objectList.isEmpty()) {
                throw new ParamException("同一业务线下不允许名称相同:" + strategyEntity.getStrategyName());
            }
        }
    }

}
