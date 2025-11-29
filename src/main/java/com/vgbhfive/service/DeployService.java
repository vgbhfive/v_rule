package com.vgbhfive.service;

import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.deploy.SceneParams;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/29 23:08
 */
public interface DeployService {

    ResponseContent diff(String sceneNo, String sceneType);

    SceneParams buildSceneParams(String sceneNo);

}
