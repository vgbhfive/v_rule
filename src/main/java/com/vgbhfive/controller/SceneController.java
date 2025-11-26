package com.vgbhfive.controller;

import com.vgbhfive.common.aop.Log;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.line.LineQueryParam;
import com.vgbhfive.dto.scene.SceneQueryParam;
import com.vgbhfive.entity.SceneEntity;
import com.vgbhfive.service.SceneService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @projectName: v-rule
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
        return sceneService.create(sceneEntity);
    }

    @PostMapping("/update")
    @Log
    public ResponseContent update(@RequestBody SceneEntity sceneEntity) {
        return sceneService.update(sceneEntity);
    }


}
