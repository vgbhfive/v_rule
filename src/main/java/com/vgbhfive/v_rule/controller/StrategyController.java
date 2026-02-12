package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.CheckParams;
import com.vgbhfive.v_rule.common.aop.Log;
import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
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

    @PostMapping("/list")
    @Log
    @Permission
    public ResponseContent queryList(@Valid @RequestBody StrategyQueryParam param) {
        return strategyService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    @Permission
    @CheckParams(type = ElementTypes.STRATEGY)
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
    @CheckParams(type = ElementTypes.STRATEGY, isUpdate = true)
    public ResponseContent update(@RequestBody StrategyEntity strategyEntity) {
        return strategyService.update(strategyEntity);
    }

    @PostMapping("/valid")
    @Log
    @Permission
    public ResponseContent updateValid(@RequestBody StrategyEntity strategyEntity) {
        return strategyService.updateValid(strategyEntity.getId(), strategyEntity.getIsValid());
    }

    @PostMapping("/dropdown/list")
    @Log
    @Permission
    public ResponseContent dropdownList(@RequestBody StrategyQueryParam param) {
        return strategyService.dropdownList(param);
    }

}
