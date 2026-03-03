package com.vgbhfive.v_rule.dto.divide;

import com.vgbhfive.v_rule.dto.PageRequest;
import lombok.Data;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 12:25
 */
@Data
public class DivideQueryParam extends PageRequest {

    private String lineNo;

    private String divideName;

    private String divideNo;

    private Integer isValid;

    private List<String> lineNoList;

}
