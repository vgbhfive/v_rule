package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.datasource.DataSourceQueryParam;
import com.vgbhfive.v_rule.entity.DataSourceEntity;

/**
 * @Author vgbhfive
 * @Date 2025/11/26 15:39
 */
public interface DataSourceService {

    ResponseContent queryList(DataSourceQueryParam param);

    ResponseContent create(DataSourceEntity dataSourceEntity, boolean isUpdate);

    ResponseContent update(DataSourceEntity dataSourceEntity);

}
