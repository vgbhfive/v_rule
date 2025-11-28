package com.vgbhfive.dto.strategy;

import com.vgbhfive.dto.PageRequest;
import lombok.Data;

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

}
