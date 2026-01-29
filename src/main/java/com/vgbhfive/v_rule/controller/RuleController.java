package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.rule.RuleQueryParam;
import com.vgbhfive.v_rule.entity.RuleEntity;
import com.vgbhfive.v_rule.service.RuleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 16:00
 */
@RestController
@RequestMapping("/rule")
public class RuleController {

    @Resource
    private RuleService ruleService;

    @PostMapping("/list")
    @Log
    @Permission
    public ResponseContent queryList(@Valid @RequestBody RuleQueryParam param) {
        return ruleService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    @Permission
    public ResponseContent create(@RequestBody RuleEntity ruleEntity) {
        return ruleService.create(ruleEntity, false);
    }

    @PostMapping("/update")
    @Log
    @Permission
    public ResponseContent update(@RequestBody RuleEntity ruleEntity) {
        return ruleService.update(ruleEntity);
    }

    @PostMapping("/valid")
    @Log
    @Permission
    public ResponseContent valid(@RequestBody RuleEntity ruleEntity) {
        return ruleService.updateValid(ruleEntity.getId(), ruleEntity.getIsValid());
    }

    @GetMapping("/dropdown/list")
    @Log
    @Permission
    public ResponseContent dropdownList() {
        return ruleService.dropdownList();
    }

}
