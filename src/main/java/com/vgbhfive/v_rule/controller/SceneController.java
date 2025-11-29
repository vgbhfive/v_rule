package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.scene.SceneQueryParam;
import com.vgbhfive.v_rule.entity.SceneEntity;
import com.vgbhfive.v_rule.service.SceneService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/23 22:53
 */
@RestController
@RequestMapping("/scene")
public class SceneController {

    @Resource
    private SceneService sceneService;

    @PostMapping("/list")
    @Log
    public ResponseContent queryList(@RequestBody SceneQueryParam param) {
        return sceneService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    public ResponseContent create(@RequestBody SceneEntity sceneEntity) {
        return sceneService.create(sceneEntity, false);
    }

    @PostMapping("/update")
    @Log
    public ResponseContent update(@RequestBody SceneEntity sceneEntity) {
        return sceneService.update(sceneEntity);
    }


}
