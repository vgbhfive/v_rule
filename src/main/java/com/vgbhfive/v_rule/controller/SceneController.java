package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.scene.SceneQueryParam;
import com.vgbhfive.v_rule.entity.SceneEntity;
import com.vgbhfive.v_rule.service.SceneService;
import org.springframework.web.bind.annotation.*;

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
    @Permission
    public ResponseContent queryList(@RequestBody SceneQueryParam param) {
        return sceneService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    @Permission
    public ResponseContent create(@RequestBody SceneEntity sceneEntity) {
        return sceneService.create(sceneEntity, false);
    }

    @PostMapping("/update")
    @Log
    @Permission
    public ResponseContent update(@RequestBody SceneEntity sceneEntity) {
        return sceneService.update(sceneEntity);
    }

    @PostMapping("/valid")
    @Log
    @Permission
    public ResponseContent valid(@RequestBody SceneEntity sceneEntity) {
        return sceneService.updateValid(sceneEntity.getId(), sceneEntity.getIsValid());
    }

    @PostMapping("/dropdown/list")
    @Log
    @Permission
    public ResponseContent dropdownList(@RequestBody SceneQueryParam param) {
        return sceneService.dropdownList(param);
    }

}
