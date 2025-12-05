package com.vgbhfive.v_rule.dto.deploy;

import com.vgbhfive.v_rule.common.enums.PeriodType;
import com.vgbhfive.v_rule.common.enums.ValueType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/12/1 10:45
 */
public class SceneStruct {

    /**
     * 业务线
     */
    @Data
    @AllArgsConstructor
    public static class Line {
        private String lineNo;
    }

    /**
     * 场景
     */
    @Data
    public static class Scene {
        private String lineNo;
        private String sceneNo;
        private String sceneName;
        private String field;
    }

    /**
     * 分流器
     */
    @Data
    public static class Divide {
        private String divideNo;
        private String divideName;
        private String sceneNo;
        private Integer priority;
        private String accessStrategyNo;
        private String riskStrategyNo;
        private Integer version;
        private Integer isValid;
        private List<String> productInterestNoList;
        private List<String> productPeriodNoList;
        private List<String> productLimitNoList;
        private List<String> productCustomNoList;
    }

    /**
     * 策略集
     */
    @Data
    public static class Strategy {
        private String strategyNo;
        private String strategyName;
        private String model;
        private List<String> ruleNoList;
        private List<String> ruleSetNoList;
        private Integer version;
        private Integer isValid;
        private List<StrategyDetail> strategyDetailList;
    }

    @Data
    public static class StrategyDetail {
        private String strategyNo;
        private String detailNo;
        private String ruleType;
        private Integer priority;
        private Integer version;
    }

    /**
     * 利率产品
     */
    @Data
    public static class ProductInterest {
        private String productNo;
        private String productName;
        private String valueType;
        private String value;
        private String unit;
        private String remark;
        private Integer version;
    }

    /**
     * 账期产品
     */
    @Data
    public static class ProductPeriod {
        private String productNo;
        private String productName;
        private String valueType;
        private String value;
        private String periodType;
        private String remark;
        private Integer version;
    }

    /**
     * 额度产品
     */
    @Data
    public static class ProductLimit {
        private String productNo;
        private String productName;
        private String valueType;
        private String value;
        private String remark;
        private Integer version;
    }

    /**
     * 自定义产品
     */
    @Data
    public static class ProductCustom {
        private String productNo;
        private String productName;
        private String remark;
        private List<ProductCustomDetail> productCustomDetailList;
    }

    @Data
    public static class ProductCustomDetail {
        private String key;
        private String valueType;
        private String value;
        private String version;
    }

    /**
     * 规则
     */
    @Data
    public static class Rule {
        private String ruleNo;
        private String ruleName;
        private String dataSourceNo;
        private String cond;
        private String threshold;
        private String thresholdType;
        private String result;
        private Integer version;
        private Integer isValid;
    }

    /**
     * 规则集
     */
    @Data
    public static class RuleSet {
        private String ruleSetNo;
        private String ruleSetName;
        private String firstNo;
        private String firstType;
        private String secondNo;
        private String secondType;
        private String combine;
        private String cond;
        private String threshold;
        private String thresholdType;
        private String result;
        private Integer version;
        private Integer isValid;
    }

    /**
     * 数据源
     */
    @Data
    public static class DataSource {
        private String dataSourceNo;
        private String dataSourceName;
        private String dataCategoryNo;
        private String field;
        private String type;
        private String format;
        private Integer version;
        private Integer isValid;
    }

    /**
     * 数据分类
     */
    @Data
    public static class DataCategory {
        private String dataCategoryNo;
        private String dataCategoryName;
        private String sourceFrom;
        private String sourceType;
        private Integer categoryType;
        private Integer priority;
        private Integer version;
        private Integer isValid;
    }

}
