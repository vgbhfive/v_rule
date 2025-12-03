package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.user.ChangePasswordParam;
import com.vgbhfive.v_rule.dto.user.LoginReq;
import com.vgbhfive.v_rule.entity.UserEntity;
import com.vgbhfive.v_rule.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/30 14:56
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    @Log
    public ResponseContent login(@RequestBody LoginReq loginReq) {
        return userService.login(loginReq);
    }

    @PostMapping("/register")
    @Log
    public ResponseContent register(@RequestBody UserEntity userEntity) {
        return userService.register(userEntity);
    }

    @PostMapping("/resetPassword")
    @Log
    public ResponseContent resetPassword(@RequestParam("email") String email) {
        return userService.resetPassword(email);
    }

    @PostMapping("/changePassword")
    @Log
    public ResponseContent changePassword(@RequestBody ChangePasswordParam param) {
        return userService.changePassword(param);
    }

}
