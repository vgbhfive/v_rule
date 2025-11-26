package com.vgbhfive.service;

import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.scene.SceneQueryParam;
import com.vgbhfive.entity.SceneEntity;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/23 23:16
 */
public interface SceneService {

    ResponseContent queryList(SceneQueryParam param);

    ResponseContent create(SceneEntity sceneEntity);

    ResponseContent update(SceneEntity sceneEntity);

}
