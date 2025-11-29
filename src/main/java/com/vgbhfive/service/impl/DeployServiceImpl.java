package com.vgbhfive.service.impl;

import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.deploy.SceneParams;
import com.vgbhfive.service.DeployService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/29 23:10
 */
@Service
public class DeployServiceImpl implements DeployService {

    /**
     * 上次上线版本与当前版本的差异
     * @param sceneNo
     * @param sceneType
     * @return
     */
    @Override
    public ResponseContent diff(String sceneNo, String sceneType) {
        return ResponseContent.success(sceneNo + " " + sceneType);
    }

    /**
     * 构建场景的策略数据
     * @param sceneNo
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public SceneParams buildSceneParams(String sceneNo) {
        return null;
    }

}
