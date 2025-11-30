package com.vgbhfive.v_rule.dto.deploy;

import com.vgbhfive.v_rule.dto.PageRequest;
import lombok.Data;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/30 14:03
 */
@Data
public class DeployQueryParam extends PageRequest {

    private String lineNo;

    /**
     * @see com.vgbhfive.v_rule.common.enums.SceneType
     */
    private String deployType;

    private String no;

    private String field;

    private String name;

    private Integer isValid;

}
