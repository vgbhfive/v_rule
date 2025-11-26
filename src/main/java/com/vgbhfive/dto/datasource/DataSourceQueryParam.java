package com.vgbhfive.dto.datasource;

import com.vgbhfive.dto.PageRequest;
import lombok.Data;

/**
 * @Author vgbhfive
 * @Date 2025/11/26 12:15
 */
@Data
public class DataSourceQueryParam extends PageRequest {

    private String name;

}
