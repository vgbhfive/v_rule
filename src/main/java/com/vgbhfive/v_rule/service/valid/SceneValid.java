package com.vgbhfive.v_rule.service.valid;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vgbhfive.v_rule.common.aop.Valid;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.common.exception.ParamException;
import com.vgbhfive.v_rule.entity.SceneEntity;
import com.vgbhfive.v_rule.mapper.SceneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author vgbhfive
 * @Date 2026/3/12 15:21
 */
@Service
@Valid(type = ElementTypes.SCENE)
public class SceneValid implements ValidService<SceneEntity> {

    @Autowired
    private SceneMapper sceneMapper;

    @Override
    public void checkParams(SceneEntity sceneEntity, boolean isUpdate) {
        List<SceneEntity> objectList = sceneMapper.selectList(new QueryWrapper<SceneEntity>()
                .eq("scene_name", sceneEntity.getSceneName()).eq("line_no", sceneEntity.getLineNo()).eq("is_delete", 0));
        if (isUpdate) {
            for (SceneEntity entity : objectList) {
                if (!sceneEntity.getId().equals(entity.getId())) {
                    throw new ParamException("同一业务线下不允许名称相同:" + sceneEntity.getSceneName());
                }
            }
        } else {
            // 新增
            if (Objects.nonNull(objectList) && !objectList.isEmpty()) {
                throw new ParamException("同一业务线下不允许名称相同:" + sceneEntity.getSceneName());
            }
        }
    }

}
