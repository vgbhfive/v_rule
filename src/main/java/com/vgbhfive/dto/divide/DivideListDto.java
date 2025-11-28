package com.vgbhfive.dto.divide;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 14:35
 */
@Data
public class DivideListDto {

    private Integer id;

    private String lineNo;

    private String sceneNo;

    private String divideName;

    private String divideNo;

    private String accessStrategyNo;

    private String riskStrategyNo;

    private Integer version;

    private Date deployAt;

    private Integer isValid;

    private Integer isDelete;

    private Date createAt;

    private List<DivideProductDto> productDtoList;

}
