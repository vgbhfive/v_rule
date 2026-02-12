package com.vgbhfive.v_rule.service.valid;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vgbhfive.v_rule.common.aop.Valid;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.common.exception.ParamException;
import com.vgbhfive.v_rule.entity.RuleEntity;
import com.vgbhfive.v_rule.entity.RuleSetEntity;
import com.vgbhfive.v_rule.mapper.RuleSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author vgbhfive
 * @Date 2026/2/6 12:15
 */
@Service
@Valid(type = ElementTypes.RULE_SET)
public class RuleSetValid implements ValidService<RuleSetEntity> {

    @Autowired
    private RuleSetMapper ruleSetMapper;

    @Override
    public void checkParams(RuleSetEntity ruleSetEntity, boolean isUpdate) {
        List<RuleSetEntity> objectList = ruleSetMapper.selectList(new QueryWrapper<RuleSetEntity>()
                .eq("rule_set_name", ruleSetEntity.getRuleSetName()).eq("line_no", ruleSetEntity.getLineNo()).eq("is_delete", 0));
        if (isUpdate) {
            for (RuleSetEntity entity : objectList) {
                if (!ruleSetEntity.getId().equals(entity.getId())) {
                    throw new ParamException("同一业务线下不允许名称相同:" + ruleSetEntity.getRuleSetName());
                }
            }
        } else {
            // 新增
            if (Objects.nonNull(objectList) && !objectList.isEmpty()) {
                throw new ParamException("同一业务线下不允许名称相同:" + ruleSetEntity.getRuleSetName());
            }
        }

        // 循环依赖
        if (isUpdate && (ruleSetEntity.getRuleSetNo().equals(ruleSetEntity.getFirstNo()) ||
                ruleSetEntity.getRuleSetNo().equals(ruleSetEntity.getSecondNo()))) {
            throw new ParamException("不允许循环依赖:" + (ruleSetEntity.getRuleSetNo().equals(ruleSetEntity.getFirstNo()) ? ruleSetEntity.getFirstNo() : ruleSetEntity.getSecondNo()));
        }
    }

}
