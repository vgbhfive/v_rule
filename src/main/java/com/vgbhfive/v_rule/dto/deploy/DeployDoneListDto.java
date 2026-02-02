package com.vgbhfive.v_rule.dto.deploy;

import lombok.Data;

import java.util.Date;

/**
 * @Author vgbhfive
 * @Date 2026/2/2 14:28
 */
@Data
public class DeployDoneListDto {

    private Integer id;

    private String lineNo;

    private String deployType;

    private String no;

    private String name;

    private String field;

    private String deployDesc;

    private Integer statusCode;

    private Date createAt;

}
