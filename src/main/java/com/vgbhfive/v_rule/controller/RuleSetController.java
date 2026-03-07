package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.CheckParams;
import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.common.enums.PermissionType;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.ruleSet.RuleSetQueryParam;
import com.vgbhfive.v_rule.entity.RuleEntity;
import com.vgbhfive.v_rule.entity.RuleSetEntity;
import com.vgbhfive.v_rule.service.RuleSetService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 16:42
 */
@RestController
@RequestMapping("/rule_set")
public class RuleSetController {

    @Resource
    private RuleSetService ruleSetService;

    @PostMapping("/list")
    @Log
    @Permission(sign = "rule_set_manage", checkPermission = true, type = PermissionType.PAGE)
    public ResponseContent queryList(@Valid @RequestBody RuleSetQueryParam param) {
        return ruleSetService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    @Permission(sign = "rule_set_manage_create", checkPermission = true, type = PermissionType.BUTTON)
    @CheckParams(type = ElementTypes.RULE_SET)
    public ResponseContent create(@RequestBody RuleSetEntity ruleSetEntity) {
        return ruleSetService.create(ruleSetEntity, false);
    }

    @PostMapping("/update")
    @Log
    @Permission(sign = "rule_set_manage_update", checkPermission = true, type = PermissionType.BUTTON)
    @CheckParams(type = ElementTypes.RULE_SET, isUpdate = true)
    public ResponseContent update(@RequestBody RuleSetEntity ruleSetEntity) {
        return ruleSetService.update(ruleSetEntity);
    }

    @PostMapping("/valid")
    @Log
    @Permission(sign = "rule_set_manage_valid", checkPermission = true, type = PermissionType.BUTTON)
    public ResponseContent valid(@RequestBody RuleEntity ruleEntity) {
        return ruleSetService.updateValid(ruleEntity.getId(), ruleEntity.getIsValid());
    }

    @PostMapping("/dropdown/list")
    @Log
    @Permission
    public ResponseContent dropdownList(@RequestBody RuleSetQueryParam param) {
        return ruleSetService.dropdownList(param);
    }

}
