package com.vgbhfive.v_rule.dto.deploy;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/12/6 16:33
 */
@Data
@AllArgsConstructor
public class DeploySceneStruct {

    private String coreVersion;

    private Map<String, String> kv;

    private String lineNo;

    private String sceneNo;

}
