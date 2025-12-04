package com.vgbhfive.v_rule.dto.scene;

import lombok.Data;

import java.util.Date;

/**
 * @Author vgbhfive
 * @Date 2025/11/26 11:25
 */
@Data
public class SceneListDto {

    private Integer id;

    private String lineNo;

    private String sceneName;

    private String field;

    private String sceneNo;

    private Integer isValid;

    private Date createAt;

}
