package com.vgbhfive.controller;

import com.vgbhfive.common.aop.Log;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.rule.RuleQueryParam;
import com.vgbhfive.dto.ruleSet.RuleSetQueryParam;
import com.vgbhfive.entity.RuleEntity;
import com.vgbhfive.entity.RuleSetEntity;
import com.vgbhfive.service.RuleService;
import com.vgbhfive.service.RuleSetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseContent queryList(@Valid @RequestBody RuleSetQueryParam param) {
        return ruleSetService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    public ResponseContent create(@RequestBody RuleSetEntity ruleSetEntity) {
        return ruleSetService.create(ruleSetEntity, false);
    }

    @PostMapping("/update")
    @Log
    public ResponseContent update(@RequestBody RuleSetEntity ruleSetEntity) {
        return ruleSetService.update(ruleSetEntity);
    }


}
