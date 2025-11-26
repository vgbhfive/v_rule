package com.vgbhfive.service;

import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.datasource.DataSourceQueryParam;
import com.vgbhfive.entity.DataSourceEntity;

/**
 * @Author vgbhfive
 * @Date 2025/11/26 15:39
 */
public interface DataSourceService {

    ResponseContent queryList(DataSourceQueryParam param);

    ResponseContent create(DataSourceEntity dataSourceEntity, boolean isUpdate);

    ResponseContent update(DataSourceEntity dataSourceEntity);

}
