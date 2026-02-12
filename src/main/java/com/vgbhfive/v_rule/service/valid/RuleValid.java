package com.vgbhfive.v_rule.service.valid;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vgbhfive.v_rule.common.aop.Valid;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.common.exception.ParamException;
import com.vgbhfive.v_rule.entity.DataSourceEntity;
import com.vgbhfive.v_rule.entity.RuleEntity;
import com.vgbhfive.v_rule.mapper.RuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author vgbhfive
 * @Date 2026/2/6 12:15
 */
@Service
@Valid(type = ElementTypes.RULE)
public class RuleValid implements ValidService<RuleEntity> {

    @Autowired
    private RuleMapper ruleMapper;

    @Override
    public void checkParams(RuleEntity ruleEntity, boolean isUpdate) {
        List<RuleEntity> objectList = ruleMapper.selectList(new QueryWrapper<RuleEntity>()
                .eq("rule_name", ruleEntity.getRuleName()).eq("line_no", ruleEntity.getLineNo()).eq("is_delete", 0));
        if (isUpdate) {
            for (RuleEntity entity : objectList) {
                if (!ruleEntity.getId().equals(entity.getId())) {
                    throw new ParamException("同一业务线下不允许名称相同:" + ruleEntity.getRuleName());
                }
            }
        } else {
            // 新增
            if (Objects.nonNull(objectList) && !objectList.isEmpty()) {
                throw new ParamException("同一业务线下不允许名称相同:" + ruleEntity.getRuleName());
            }
        }
    }

}
