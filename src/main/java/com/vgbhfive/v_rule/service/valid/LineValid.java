package com.vgbhfive.v_rule.service.valid;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vgbhfive.v_rule.common.aop.Valid;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.common.exception.ParamException;
import com.vgbhfive.v_rule.entity.LineEntity;
import com.vgbhfive.v_rule.mapper.LineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2026/2/6 11:56
 */
@Service
@Valid(type = ElementTypes.LINE)
public class LineValid implements ValidService<LineEntity> {

    @Autowired
    private LineMapper lineMapper;

    @Override
    public void checkParams(LineEntity lineEntity, boolean isUpdate) {
        List<LineEntity> objectList = lineMapper.selectList(new QueryWrapper<LineEntity>()
                .and(wrapper -> wrapper.eq("line_name", lineEntity.getLineName()).or().eq("line_no", lineEntity.getLineNo()))
                .eq("is_delete", 0));
        if (isUpdate) {
            for (LineEntity entity : objectList) {
                if (!lineEntity.getId().equals(entity.getId())) {
                    throw new ParamException("不允许名称相同:" + lineEntity.getLineName());
                }
            }
        } else {   // 新增
            if (null != objectList && objectList.size() > 0) {
                throw new ParamException("不允许名称相同:" + lineEntity.getLineName());
            }
        }
    }

}
