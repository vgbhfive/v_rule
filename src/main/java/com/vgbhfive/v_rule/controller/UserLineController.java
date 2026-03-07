package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.common.enums.PermissionType;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.entity.UserLineEntity;
import com.vgbhfive.v_rule.service.UserLineService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2026/3/2 17:11
 */
@RestController
@RequestMapping("/user/line")
public class UserLineController {

    @Resource
    private UserLineService userLineService;

    @PostMapping("/detail/{id}")
    @Log
    @Permission(sign = "user_line_manage", checkPermission = true, type = PermissionType.PAGE)
    public ResponseContent userLineDetails(@PathVariable("id") Integer userId) {
        return userLineService.userLineDetails(userId);
    }

    /**
     * 管理员操作用户业务线权限后需要重新登录
     * @param userLineEntityList
     * @return
     */
    @PostMapping("/update")
    @Log
    @Permission(sign = "user_line_manage_update", checkPermission = true, type = PermissionType.BUTTON)
    public ResponseContent updateUserLine(@RequestBody List<UserLineEntity> userLineEntityList) {
        return userLineService.updateUserLineDetail(userLineEntityList);
    }

}
