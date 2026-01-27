package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.strategy.StrategyQueryParam;
import com.vgbhfive.v_rule.entity.StrategyEntity;
import com.vgbhfive.v_rule.service.StrategyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 12:24
 */
@RestController
@RequestMapping("/strategy")
public class StrategyController {

    @Resource
    private StrategyService strategyService;

    @GetMapping("/list")
    @Log
    @Permission
    public ResponseContent queryList(@Valid @RequestBody StrategyQueryParam param) {
        return strategyService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    @Permission
    public ResponseContent create(@RequestBody StrategyEntity strategyEntity) {
        return strategyService.create(strategyEntity, false);
    }

    @GetMapping("/detail/{id}")
    @Log
    @Permission
    public ResponseContent detail(@PathVariable("id") Integer id) {
        return strategyService.detail(id);
    }

    @PostMapping("/update")
    @Log
    @Permission
    public ResponseContent update(@RequestBody StrategyEntity strategyEntity) {
        return strategyService.update(strategyEntity);
    }

    @GetMapping("/dropdown/list")
    @Log
    @Permission
    public ResponseContent dropdownList() {
        return strategyService.dropdownList();
    }

}
