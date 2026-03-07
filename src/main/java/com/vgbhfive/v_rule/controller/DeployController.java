package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.common.enums.PermissionType;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.DeployQueryParam;
import com.vgbhfive.v_rule.dto.deploy.DeployRollBack;
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

    /**
     * 查询可上线的场景列表
     * @param param
     * @return
     */
    @PostMapping("/list")
    @Log
    @Permission(sign = "deploy_manage", checkPermission = true, type = PermissionType.PAGE)
    public ResponseContent queryList(@Valid @RequestBody DeployQueryParam param) {
        return deployService.queryList(param);
    }

    /**
     * 查询已上线的场景记录
     * @param param
     * @return
     */
    @PostMapping("/done/list")
    @Log
    @Permission
    public ResponseContent queryDeployList(@Valid @RequestBody DeployQueryParam param) {
        return deployService.queryDoneList(param);
    }

    @PostMapping("/dropdown/list")
    @Log
    @Permission
    public ResponseContent dropdownList(@RequestBody DeployQueryParam param) {
        return deployService.dropdownList(param);
    }

    /**
     * 上次上线版本与当前版本的差异
     * @param sceneNo
     * @param sceneType
     * @return
     */
    @PostMapping("/diff")
    @Log
    @Permission(sign = "deploy_manage_diff", checkPermission = true, type = PermissionType.BUTTON)
    public ResponseContent diff(@RequestParam("no") String sceneNo, @RequestParam("type") String sceneType) throws Exception {
        return deployService.diff(sceneNo, sceneType);
    }

    /**
     * 通过审核并构建场景的策略数据
     * @param deployEntity
     * @return
     */
    @PostMapping("/pass")
    @Log
    @Permission(sign = "deploy_manage_deploy", checkPermission = true, type = PermissionType.BUTTON)
    public ResponseContent pass(@RequestBody DeployEntity deployEntity) {
        return deployService.pass(deployEntity);
    }

    /**
     * 回滚场景版本列表
     * @param deployEntity
     * @return
     */
    @PostMapping("/rollback/versions")
    @Log
    @Permission
    public ResponseContent rollbackVersionList(@RequestBody DeployEntity deployEntity) {
        return deployService.rollbackVersionList(deployEntity.getDeployNo());
    }

    /**
     * 回滚场景
     * @return
     */
    @PostMapping("/rollback")
    @Log
    @Permission(sign = "deploy_manage_rollback", checkPermission = true, type = PermissionType.BUTTON)
    public ResponseContent rollback(@RequestBody DeployRollBack deployRollBack) {
        return deployService.rollback(deployRollBack);
    }

}
