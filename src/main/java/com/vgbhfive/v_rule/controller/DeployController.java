package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.DeployQueryParam;
import com.vgbhfive.v_rule.entity.DeployEntity;
import com.vgbhfive.v_rule.service.DeployService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/29 1:09
 */
@RestController
@RequestMapping("/deploy")
public class DeployController {

    @Resource
    private DeployService deployService;

    @GetMapping("/scene/list")
    @Log
    public ResponseContent queryList(@Valid @RequestBody DeployQueryParam param) {
        return ResponseContent.success();
    }

    /**
     * 上次上线版本与当前版本的差异
     * @param sceneNo
     * @param sceneType
     * @return
     */
    @PostMapping("/diff")
    @Log
    public ResponseContent diff(@RequestParam("no") String sceneNo, @RequestParam("type") String sceneType) {
        return deployService.diff(sceneNo, sceneType);
    }

    /**
     * 构建场景的策略数据
     * @param deployEntity
     * @return
     */
    @PostMapping("/pass")
    @Log
    public ResponseContent pass(@RequestBody DeployEntity deployEntity) {
        return deployService.pass(deployEntity);
    }

}
