package com.vgbhfive.v_rule.dto.strategy;

import com.vgbhfive.v_rule.dto.PageRequest;
import lombok.Data;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 12:26
 */
@Data
public class StrategyQueryParam extends PageRequest {

    private String lineNo;

    private String sceneNo;

    private String strategyName;

    private String strategyNo;

    private Integer isValid;

    private List<String> lineNoList;

}
