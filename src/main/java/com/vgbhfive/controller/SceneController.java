package com.vgbhfive.controller;

import com.vgbhfive.common.aop.Log;
import com.vgbhfive.dto.ResponseContent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/23 22:53
 */
@RestController
@RequestMapping("/scene")
public class SceneController {

    @PostMapping("/list")
    @Log
    public ResponseContent queryList() {
        return ResponseContent.success();
    }

    @PostMapping("/create")
    @Log
    public ResponseContent create() {
        return ResponseContent.success();
    }

    @PostMapping("/update")
    @Log
    public ResponseContent update() {
        return ResponseContent.success();
    }


}
