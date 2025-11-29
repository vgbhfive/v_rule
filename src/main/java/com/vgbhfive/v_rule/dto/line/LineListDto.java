package com.vgbhfive.v_rule.dto.line;

import lombok.Data;

import java.util.Date;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/24 23:34
 */
@Data
public class LineListDto {

    private Integer id;

    private String lineName;

    private String lineNo;

    private String url;

    private Integer isValid;

    private Date createAt;

    private Date updateAt;

}
