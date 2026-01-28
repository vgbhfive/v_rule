package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.user.ChangePasswordParam;
import com.vgbhfive.v_rule.dto.user.LoginReq;
import com.vgbhfive.v_rule.dto.user.UserQueryParam;
import com.vgbhfive.v_rule.entity.UserEntity;
import com.vgbhfive.v_rule.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    @GetMapping("/info")
    @Log
    @Permission
    public ResponseContent info(HttpServletRequest httpServletRequest) {
        return userService.info(httpServletRequest);
    }

    @PostMapping("/register")
    @Log
    @Permission
    public ResponseContent register(@RequestBody UserEntity userEntity) {
        return userService.register(userEntity);
    }

    @PostMapping("/resetPassword")
    @Log
    @Permission
    public ResponseContent resetPassword(@RequestParam("email") String email) {
        return userService.resetPassword(email);
    }

    @PostMapping("/changePassword")
    @Log
    @Permission
    public ResponseContent changePassword(@RequestBody ChangePasswordParam param) {
        return userService.changePassword(param);
    }

    @PostMapping("/refreshToken")
    @Log
    @Permission
    public ResponseContent refreshToken(HttpServletRequest httpServletRequest) {
        return userService.refreshToken(httpServletRequest);
    }

    @PostMapping("/list")
    @Log
    @Permission
    public ResponseContent list(@Valid @RequestBody UserQueryParam param) {
        return userService.list(param);
    }

}
