package com.vgbhfive.controller;

import com.vgbhfive.common.aop.Log;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.rule.RuleQueryParam;
import com.vgbhfive.entity.RuleEntity;
import com.vgbhfive.service.RuleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseContent queryList(@Valid @RequestBody RuleQueryParam param) {
        return ruleService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    public ResponseContent create(@RequestBody RuleEntity ruleEntity) {
        return ruleService.create(ruleEntity, false);
    }

    @PostMapping("/update")
    @Log
    public ResponseContent update(@RequestBody RuleEntity ruleEntity) {
        return ruleService.update(ruleEntity);
    }

}
