package com.vgbhfive.v_rule.dto.deploy;

import lombok.Data;

/**
 * @Author vgbhfive
 * @Date 2025/12/1 10:45
 */
public class SceneStruct {

    @Data
    public static class Line {
        private String lineNo;
    }

    @Data
    public static class Divide {
        private String divideNo;
    }

    @Data
    public static class Strategy {
        private String strategyNo;
    }

    @Data
    public static class ProductInterest {
        private String productNo;
    }

    @Data
    public static class Rule {
        private String ruleNo;
    }

    @Data
    public static class RuleSet {
        private String ruleSetNo;
    }

    @Data
    public static class DataSource {
        private String dataSourceNo;
    }

    @Data
    public static class DataCategory {
        private String sourceFrom;
        private String sourceType;
    }

}
