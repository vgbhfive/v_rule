package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.deploy.VersionDiffDetail;
import com.vgbhfive.v_rule.dto.scene.SceneQueryParam;
import com.vgbhfive.v_rule.entity.SceneEntity;

import java.util.List;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/23 23:16
 */
public interface SceneService {

    ResponseContent queryList(SceneQueryParam param);

    ResponseContent create(SceneEntity sceneEntity, boolean isUpdate);

    ResponseContent update(SceneEntity sceneEntity);

    List<VersionDiffDetail> queryDeployDiff(List<SceneStruct.Scene> sceneList, List<SceneStruct.Scene> lastSceneList) throws Exception;

}
