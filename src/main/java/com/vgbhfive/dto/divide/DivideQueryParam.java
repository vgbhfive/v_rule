package com.vgbhfive.dto.divide;

import com.vgbhfive.dto.PageRequest;
import lombok.Data;

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

}
