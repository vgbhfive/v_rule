package com.vgbhfive.v_rule.dto;

import lombok.Data;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/24 23:23
 */
@Data
public class PageRequest {

    // 当前页数
    private Integer currPage = 1;

    // 每页展示数量
    private Integer limit = 10;

}
