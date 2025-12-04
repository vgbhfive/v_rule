package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.deploy.VersionDiffDetail;
import com.vgbhfive.v_rule.dto.divide.DivideQueryParam;
import com.vgbhfive.v_rule.entity.DivideEntity;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 15:51
 */
public interface DivideService {

    ResponseContent queryList(DivideQueryParam param);

    ResponseContent create(DivideEntity divideEntity, boolean isUpdate);

    ResponseContent detail(Integer id);

    ResponseContent update(DivideEntity divideEntity);

    List<SceneStruct.Divide> queryDivideBySceneNo(String sceneNo);

    List<VersionDiffDetail> queryDeployDiff(List<SceneStruct.Divide> divideList, List<SceneStruct.Divide> lastDivideList) throws Exception;

}
