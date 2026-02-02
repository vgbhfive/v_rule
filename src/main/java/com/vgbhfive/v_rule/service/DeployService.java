package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.DeployQueryParam;
import com.vgbhfive.v_rule.dto.deploy.SceneParams;
import com.vgbhfive.v_rule.entity.DeployEntity;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/29 23:08
 */
public interface DeployService {

    ResponseContent queryList(DeployQueryParam param);

    ResponseContent queryDoneList(DeployQueryParam param);

    ResponseContent dropdownList(DeployQueryParam param);

    ResponseContent diff(String sceneNo, String sceneType) throws Exception;

    ResponseContent pass(DeployEntity deployEntity);

    SceneParams buildSceneParams(String sceneNo);

    ResponseContent rollback();

}
