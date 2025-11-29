package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.SceneParams;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/29 23:08
 */
public interface DeployService {

    ResponseContent diff(String sceneNo, String sceneType);

    SceneParams buildSceneParams(String sceneNo);

}
