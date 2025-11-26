package com.vgbhfive.service;

import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.datasource.DataSourceQueryParam;

/**
 * @Author vgbhfive
 * @Date 2025/11/26 15:39
 */
public interface DataSourceService {

    ResponseContent queryList(DataSourceQueryParam param);

}
