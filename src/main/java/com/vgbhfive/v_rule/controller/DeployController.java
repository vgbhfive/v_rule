package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.entity.DeployEntity;
import com.vgbhfive.v_rule.service.DeployService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @PostMapping("/diff")
    @Log
    public ResponseContent diff(@RequestParam("no") String sceneNo, @RequestParam("type") String sceneType) {
        return deployService.diff(sceneNo, sceneType);
    }
    
    @PostMapping("/insert")
    @Log
    public ResponseContent insert(@RequestBody DeployEntity deployEntity) {
        return ResponseContent.success();
    }

}
