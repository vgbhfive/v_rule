package com.vgbhfive.dto.scene;

import com.vgbhfive.dto.PageRequest;
import lombok.Data;

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

}
