package com.vgbhfive.v_rule.dto.deploy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/12/4 23:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionDiffDetail implements Serializable {

    private String no;

    private String name;

    private List<DetailCompareResult> results;

}
