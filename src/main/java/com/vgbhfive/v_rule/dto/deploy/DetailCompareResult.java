package com.vgbhfive.v_rule.dto.deploy;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/12/4 23:32
 */
@Data
@AllArgsConstructor
public class DetailCompareResult {

    private String name;

    private Object oldValue;

    private Object newValue;

}
