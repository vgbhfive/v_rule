package com.vgbhfive.v_rule.dto.deploy;

import lombok.Data;

/**
 * @Author vgbhfive
 * @Date 2026/2/6 11:16
 */
@Data
public class RollBackParam {

    private String deployNo;

    private Integer version;

}
