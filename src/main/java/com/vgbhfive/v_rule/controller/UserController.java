package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.dto.ResponseContent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/30 14:56
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    @Log
    public ResponseContent login() {
        return ResponseContent.success();
    }

}
