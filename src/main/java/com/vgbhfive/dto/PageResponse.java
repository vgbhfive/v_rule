package com.vgbhfive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/24 23:43
 */
@Data
@AllArgsConstructor
public class PageResponse<T> {

    // 当前页
    private Integer currPage;

    // 每页条数
    private Integer limit;

    // 总条数
    private Integer totalCount;

    // 总页数
    private Integer totalPage;

    private List<T> dataList;

}
