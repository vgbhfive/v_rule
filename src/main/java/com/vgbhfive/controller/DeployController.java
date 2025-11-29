package com.vgbhfive.controller;

import com.vgbhfive.common.aop.Log;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.entity.DeployEntity;
import com.vgbhfive.service.DeployService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @projectName: v-rule
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
