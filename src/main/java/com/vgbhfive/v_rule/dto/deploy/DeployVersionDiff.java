package com.vgbhfive.v_rule.dto.deploy;

import lombok.Data;

import java.util.List;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/30 14:02
 */
@Data
public class DeployVersionDiff {

    // 1-首次上线、0-正常上线
    private Integer deployStatus;

    private List<VersionDiffDetail> divide;

}
