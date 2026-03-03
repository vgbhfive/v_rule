package com.vgbhfive.v_rule.dto.scene;

import com.vgbhfive.v_rule.dto.PageRequest;
import lombok.Data;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/11/26 11:23
 */
@Data
public class SceneQueryParam extends PageRequest {

    private String lineNo;

    private String sceneName;

    private String field;

    private String sceneNo;

    private Integer isValid;

    private List<String> lineNoList;

}
