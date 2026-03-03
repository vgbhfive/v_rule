package com.vgbhfive.v_rule.controller;

import com.vgbhfive.v_rule.common.aop.Permission;
import com.vgbhfive.v_rule.common.enums.*;
import com.vgbhfive.v_rule.dto.ResponseContent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 18:55
 */
@RestController
@RequestMapping("/enum")
public class EnumController {

    @GetMapping("/fieldType")
    @Permission
    public ResponseContent<List<Map<String, Object>>> fieldType() {
        return ResponseContent.success(FieldType.allInstance());
    }

    @GetMapping("/valueType")
    @Permission
    public ResponseContent<List<Map<String, Object>>> valueType() {
        return ResponseContent.success(ValueType.allInstance());
    }

    @GetMapping("/categoryType")
    @Permission
    public ResponseContent<List<Map<String, Object>>> categoryType() {
        return ResponseContent.success(CategoryType.allInstance());
    }

    @GetMapping("/conditionType")
    @Permission
    public ResponseContent<List<Map<String, Object>>> conditionType() {
        return ResponseContent.success(ConditionType.allInstance());
    }

    @GetMapping("/conditionType/ruleSet")
    @Permission
    public ResponseContent<List<Map<String, Object>>> conditionTypeRuleSet() {
        return ResponseContent.success(ConditionType.allRuleSetInstance());
    }

    @GetMapping("/combineType")
    @Permission
    public ResponseContent<List<Map<String, Object>>> combineType() {
        return ResponseContent.success(CombineType.allInstance());
    }

    @GetMapping("/ruleType")
    @Permission
    public ResponseContent<List<Map<String, Object>>> ruleType() {
        return ResponseContent.success(RuleType.allInstance());
    }

    @GetMapping("/productType")
    @Permission
    public ResponseContent<List<Map<String, Object>>> productType() {
        return ResponseContent.success(ProductType.allInstance());
    }

    @GetMapping("/periodType")
    @Permission
    public ResponseContent<List<Map<String, Object>>> periodType() {
        return ResponseContent.success(PeriodType.allInstance());
    }

    @GetMapping("/interestUnitType")
    @Permission
    public ResponseContent<List<Map<String, Object>>> interestUnitType() {
        return ResponseContent.success(InterestUnitType.allInstance());
    }

    @GetMapping("/strategyModel")
    @Permission
    public ResponseContent<List<Map<String, Object>>> strategyModel() {
        return ResponseContent.success(StrategyModel.allInstance());
    }

    @GetMapping("/resultType")
    @Permission
    public ResponseContent<List<Map<String, Object>>> resultType() {
        return ResponseContent.success(ResultType.allInstance());
    }

    @GetMapping("/sceneType")
    @Permission
    public ResponseContent<List<Map<String, Object>>> sceneType() {
        return ResponseContent.success(SceneType.allInstance());
    }

}
