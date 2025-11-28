package com.vgbhfive.controller;

import com.vgbhfive.common.aop.Log;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.strategy.StrategyQueryParam;
import com.vgbhfive.entity.StrategyEntity;
import com.vgbhfive.service.StrategyService;
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
    public ResponseContent queryList(@Valid @RequestBody StrategyQueryParam param) {
        return strategyService.queryList(param);
    }

    @PostMapping("/create")
    @Log
    public ResponseContent create(@RequestBody StrategyEntity strategyEntity) {
        return strategyService.create(strategyEntity, false);
    }

    @GetMapping("/detail/{id}")
    @Log
    public ResponseContent detail(@PathVariable("id") Integer id) {
        return strategyService.detail(id);
    }

    @PostMapping("/update")
    @Log
    public ResponseContent update(@RequestBody StrategyEntity strategyEntity) {
        return strategyService.update(strategyEntity);
    }

}
