package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Permission;
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

    @GetMapping("/fieldType")
    @Permission
    public ResponseContent fieldType() {
        return ResponseContent.success(FieldType.allInstance());
    }

    @GetMapping("/valueType")
    @Permission
    public ResponseContent valueType() {
        return ResponseContent.success(ValueType.allInstance());
    }

    @GetMapping("/categoryType")
    @Permission
    public ResponseContent categoryType() {
        return ResponseContent.success(CategoryType.allInstance());
    }

    @GetMapping("/conditionType")
    @Permission
    public ResponseContent conditionType() {
        return ResponseContent.success(ConditionType.allInstance());
    }

    @GetMapping("/ruleType")
    @Permission
    public ResponseContent ruleType() {
        return ResponseContent.success(RuleType.allInstance());
    }

    @GetMapping("/productType")
    @Permission
    public ResponseContent productType() {
        return ResponseContent.success(ProductType.allInstance());
    }

    @GetMapping("/periodType")
    @Permission
    public ResponseContent periodType() {
        return ResponseContent.success(PeriodType.allInstance());
    }

    @GetMapping("/interestUnitType")
    @Permission
    public ResponseContent interestUnitType() {
        return ResponseContent.success(InterestUnitType.allInstance());
    }

    @GetMapping("/strategyModel")
    @Permission
    public ResponseContent strategyModel() {
        return ResponseContent.success(StrategyModel.allInstance());
    }

    @GetMapping("/resultType")
    @Permission
    public ResponseContent resultType() {
        return ResponseContent.success(ResultType.allInstance());
    }

}
