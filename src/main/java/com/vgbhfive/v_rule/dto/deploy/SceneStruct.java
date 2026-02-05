package com.vgbhfive.v_rule.dto.deploy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        private String no; // 编码
        private String name; // 名称
        private String field; // 字段
        private String entry; // 起始分流器
        private List<String> divideNoList; // 分流器
        private List<String> productNoList; // 产品
        private List<String> ruleNoList; // 规则
        private List<String> ruleSetNoList; // 规则集
        private List<String> dataSourceNoList; // 数据源
        private List<String> dataCategoryNoList; // 数据分类
    }

    /**
     * 分流器
     */
    @Data
    public static class Divide {
        private String no;
        private String name;
        private String sceneNo;
        private Integer priority; // 优先级
        private String accessStrategyNo;
        private String riskStrategyNo;
        private List<String> productNoList; // 产品
        private List<String> productInterestNoList; // 利率
        private List<String> productPeriodNoList; // 账期
        private List<String> productLimitNoList; // 额度
        private List<String> productCustomNoList; // 自定义
        private List<DivideNode> nodeList; // 节点
        private List<DivideDiversion> diversionItem; // 分流连线
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DivideNode {
        private String no;
        private String name;
        private String type; // value、product、diversion
        private String value;
        private List<String> productNoList;
        private String calcType;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DivideDiversion {
        private String source;
        private String value;
        private String target;
    }

    /**
     * 策略集
     */
    @Data
    public static class Strategy {
        private String no;
        private String name;
        private String type;
        private String model; // 运行模式
        private List<String> ruleNoList; // 规则
        private List<String> ruleSetNoList; // 规则集
        private List<StrategyDetail> strategyDetailList;
        private List<String> productNoList; // 产品
    }

    @Data
    public static class StrategyDetail {
        private String strategyNo;
        private String detailNo;
        private String ruleType;
        private String threshold;
        private String thresholdType;
        private Integer priority;
        private String finalDecision;
    }

    /**
     * 产品
     */
    @Data
    public static class Product {
        private String no;
        private String name;
        private String type;
        private String remark;
    }

    /**
     * 利率产品
     */
    @Data
    public static class ProductInterest extends Product {
        private String valueType;
        private String value;
        private String unit;
    }

    /**
     * 账期产品
     */
    @Data
    public static class ProductPeriod  extends Product {
        private String valueType;
        private String value;
        private String periodType;
    }

    /**
     * 额度产品
     */
    @Data
    public static class ProductLimit extends Product {
        private String valueType;
        private String value;
    }

    /**
     * 自定义产品
     */
    @Data
    public static class ProductCustom extends Product {
        private List<ProductCustomDetail> detailList;
    }

    @Data
    public static class ProductCustomDetail {
        private String key;
        private String valueType;
        private String value;
    }

    /**
     * 规则
     */
    @Data
    public static class Rule {
        private String no;
        private String name;
        private String dataSourceNo;
        private String cond;
        private String threshold;
        private String thresholdType;
        private String result;
    }

    /**
     * 规则集
     */
    @Data
    public static class RuleSet {
        private String no;
        private String name;
        private String firstNo;
        private String firstType;
        private String secondNo;
        private String secondType;
        private String combine;
        private String cond;
        private String threshold;
        private String result;
    }

    /**
     * 数据源
     */
    @Data
    public static class DataSource {
        private String no;
        private String name;
        private String dataCategoryNo;
        private String field;
        private String type;
        private String format;
    }

    /**
     * 数据分类
     */
    @Data
    public static class DataCategory {
        private String no;
        private String name;
        private String sourceFrom;
        private String sourceType;
        private Integer categoryType;
        private Integer priority;
    }

}
