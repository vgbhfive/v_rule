package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.entity.pub.OperatePermission;
import com.vgbhfive.v_rule.service.UserOperateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2026/3/3 16:07
 */
@RestController
@RequestMapping("/user/operate")
public class UserOperateController {

    @Resource
    private UserOperateService userOperateService;

    @GetMapping("/list")
    @Log
    @Permission
    public ResponseContent<List<OperatePermission>> operatePermissionList() {
        return userOperateService.operatePermissionList();
    }

}
