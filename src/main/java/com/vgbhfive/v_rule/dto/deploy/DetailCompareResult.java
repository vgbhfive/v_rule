package com.vgbhfive.v_rule.dto.deploy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/12/4 23:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailCompareResult implements Serializable {

    private String name;

    private Object oldValue;

    private Object newValue;

}
