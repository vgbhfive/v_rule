package com.vgbhfive.v_rule.dto.deploy;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/30 14:02
 */
@Data
public class DeployVersionDiff implements Serializable {

    // 1-首次上线、0-正常上线
    private Integer deployStatus;

    private List<VersionDiffDetail> scene;

    private List<VersionDiffDetail> divide;

    private List<VersionDiffDetail> strategy;

    private List<VersionDiffDetail> interest;

    private List<VersionDiffDetail> period;

    private List<VersionDiffDetail> limit;

    private List<VersionDiffDetail> custom;

    private List<VersionDiffDetail> rule;

    private List<VersionDiffDetail> ruleSet;

    private List<VersionDiffDetail> dataSource;

    private List<VersionDiffDetail> dataCategory;

}
