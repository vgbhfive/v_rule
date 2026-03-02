package com.vgbhfive.v_rule.dto.deploy;

import lombok.Data;

/**
 * @Author vgbhfive
 * @Date 2026/3/2 14:40
 */
@Data
public class DeployRollBack {

    /**
     * 类型
     * @see com.vgbhfive.v_rule.common.enums.SceneType
     */
    private String type;

    /**
     * 编码
     */
    private String deployNo;

    /**
     * 版本
     */
    private Integer version;

}
