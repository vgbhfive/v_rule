package com.vgbhfive.dto.line;

import com.vgbhfive.dto.PageRequest;
import lombok.Data;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/24 23:18
 */
@Data
public class LineQueryParam extends PageRequest {

    private String lineNo;

    private String lineName;

    private Integer isValid;

}
