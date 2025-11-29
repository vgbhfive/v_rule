package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.scene.SceneQueryParam;
import com.vgbhfive.v_rule.entity.SceneEntity;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/23 23:16
 */
public interface SceneService {

    ResponseContent queryList(SceneQueryParam param);

    ResponseContent create(SceneEntity sceneEntity, boolean isUpdate);

    ResponseContent update(SceneEntity sceneEntity);

}
