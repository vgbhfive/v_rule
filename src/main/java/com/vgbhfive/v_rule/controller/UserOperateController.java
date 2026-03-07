package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.common.enums.PermissionType;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.entity.UserLineEntity;
import com.vgbhfive.v_rule.entity.UserOperateEntity;
import com.vgbhfive.v_rule.entity.pub.OperatePermission;
import com.vgbhfive.v_rule.service.UserOperateService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/detail/{id}")
    @Log
    @Permission(sign = "user_operate_manage", checkPermission = true, type = PermissionType.PAGE)
    public ResponseContent<List<Integer>> userLineDetails(@PathVariable("id") Integer userId) {
        return userOperateService.userOperateDetails(userId);
    }

    /**
     * 管理员操作用户操作权限后需要重新登录
     * @param userOperateEntityList
     * @return
     */
    @PostMapping("/update")
    @Log
    @Permission(sign = "user_operate_manage_update", checkPermission = true, type = PermissionType.BUTTON)
    public ResponseContent updateUserLine(@RequestBody List<UserOperateEntity> userOperateEntityList) {
        return userOperateService.updateUserOperateDetail(userOperateEntityList);
    }


}
