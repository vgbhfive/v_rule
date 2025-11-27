package com.vgbhfive.controller;

import com.vgbhfive.common.aop.Log;
import com.vgbhfive.common.enums.*;
import com.vgbhfive.dto.ResponseContent;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 18:55
 */
@RestController
@RequestMapping("/enum")
public class EnumController {

    @GetMapping("/valueType")
    public ResponseContent valueTypes() {
        return ResponseContent.success(ValueType.allInstance());
    }

    @GetMapping("/strategyModel")
    public ResponseContent strategyModel() {
        return ResponseContent.success(StrategyModel.values());
    }

    @GetMapping("/ruleType")
    public ResponseContent ruleType() {
        return ResponseContent.success(RuleType.values());
    }

    @GetMapping("/productType")
    public ResponseContent productType() {
        return ResponseContent.success(ProductType.values());
    }

    @GetMapping("/periodType")
    public ResponseContent periodType() {
        return ResponseContent.success(PeriodType.values());
    }

    @GetMapping("/interestUnitType")
    public ResponseContent interestUnitType() {
        return ResponseContent.success(InterestUnitType.values());
    }

}
