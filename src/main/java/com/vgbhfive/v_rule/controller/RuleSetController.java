package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.ruleSet.RuleSetQueryParam;
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

    @GetMapping("/list")
    @Log
    @Permission
    public ResponseContent queryList(@Valid @RequestBody RuleSetQueryParam param) {
        return ruleSetService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    @Permission
    public ResponseContent create(@RequestBody RuleSetEntity ruleSetEntity) {
        return ruleSetService.create(ruleSetEntity, false);
    }

    @PostMapping("/update")
    @Log
    @Permission
    public ResponseContent update(@RequestBody RuleSetEntity ruleSetEntity) {
        return ruleSetService.update(ruleSetEntity);
    }

    @GetMapping("/dropdown/list")
    @Log
    @Permission
    public ResponseContent dropdownList() {
        return ruleSetService.dropdownList();
    }

}
