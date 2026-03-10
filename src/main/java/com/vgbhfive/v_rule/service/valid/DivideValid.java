package com.vgbhfive.v_rule.service.valid;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vgbhfive.v_rule.common.aop.Valid;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.common.exception.ParamException;
import com.vgbhfive.v_rule.entity.DataSourceEntity;
import com.vgbhfive.v_rule.entity.DivideEntity;
import com.vgbhfive.v_rule.mapper.DivideMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author vgbhfive
 * @Date 2026/2/6 11:58
 */
@Service
@Valid(type = ElementTypes.DIVIDE)
public class DivideValid implements ValidService<DivideEntity> {

    @Autowired
    private DivideMapper divideMapper;

    @Override
    public void checkParams(DivideEntity divideEntity, boolean isUpdate) {
        List<DivideEntity> objectList = divideMapper.selectList(new QueryWrapper<DivideEntity>()
                .eq("divide_name", divideEntity.getDivideName()).eq("line_no", divideEntity.getLineNo()).eq("is_delete", 0));
        if (isUpdate) {
            for (DivideEntity entity : objectList) {
                if (!divideEntity.getId().equals(entity.getId())) {
                    throw new ParamException("同一业务线下不允许名称相同:" + divideEntity.getDivideName());
                }
            }
        } else {
            // 新增
            if (Objects.nonNull(objectList) && !objectList.isEmpty()) {
                throw new ParamException("同一业务线下不允许名称相同:" + divideEntity.getDivideName());
            }
        }

        // 1. 所属同一场景下不允许存在相同优先级的分流器
        List<DivideEntity> priorityList = divideMapper.selectList(new QueryWrapper<DivideEntity>().eq("line_no", divideEntity.getLineNo()).
                eq("scene_no", divideEntity.getSceneNo()).
                ne("divide_no", divideEntity.getDivideNo()).
                eq("priority", divideEntity.getPriority()).
                eq("is_delete", 0));
        if (Objects.nonNull(priorityList) && !priorityList.isEmpty()) {
            throw new ParamException("同一场景下不允许存在相同优先级的分流器:" + divideEntity.getDivideName());
        }

    }

}
