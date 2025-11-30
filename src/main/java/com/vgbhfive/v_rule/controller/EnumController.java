package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.enums.*;
import com.vgbhfive.v_rule.dto.ResponseContent;
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
    public ResponseContent valueType() {
        return ResponseContent.success(ValueType.allInstance());
    }

    @GetMapping("/fieldType")
    public ResponseContent fieldType() {
        return ResponseContent.success(FieldType.allInstance());
    }

    @GetMapping("/strategyModel")
    public ResponseContent strategyModel() {
        return ResponseContent.success(StrategyModel.allInstance());
    }

    @GetMapping("/ruleType")
    public ResponseContent ruleType() {
        return ResponseContent.success(RuleType.allInstance());
    }

    @GetMapping("/productType")
    public ResponseContent productType() {
        return ResponseContent.success(ProductType.allInstance());
    }

    @GetMapping("/periodType")
    public ResponseContent periodType() {
        return ResponseContent.success(PeriodType.allInstance());
    }

    @GetMapping("/interestUnitType")
    public ResponseContent interestUnitType() {
        return ResponseContent.success(InterestUnitType.allInstance());
    }

    @GetMapping("/resultType")
    public ResponseContent resultType() {
        return ResponseContent.success(ResultType.allInstance());
    }

}
