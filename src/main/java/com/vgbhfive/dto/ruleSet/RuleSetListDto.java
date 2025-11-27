package com.vgbhfive.dto.ruleSet;

import lombok.Data;

import java.util.Date;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 16:45
 */
@Data
public class RuleSetListDto {

    private Integer id;

    private String lineNo;

    private String ruleSetName;

    private String ruleSetNo;

    private String firstNo;

    private String firstType;

    private String secondNo;

    private String secondType;

    private String combine;

    private String cond;

    private String threshold;

    private String result;

    private Integer version;

    private Date deployAt;

    private Integer isValid;

    private Integer isDelete;

}
