package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.datasource.DataSourceQueryParam;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.deploy.VersionDiffDetail;
import com.vgbhfive.v_rule.entity.DataSourceEntity;

import java.util.List;
import java.util.Set;

/**
 * @Author vgbhfive
 * @Date 2025/11/26 15:39
 */
public interface DataSourceService {

    ResponseContent queryList(DataSourceQueryParam param);

    ResponseContent create(DataSourceEntity dataSourceEntity, boolean isUpdate);

    ResponseContent update(DataSourceEntity dataSourceEntity);

    ResponseContent updateValid(Integer id, Integer status);

    List<SceneStruct.DataSource> queryDataSourceByDataSourceNos(Set<String> dataSourceNoSet);

    List<VersionDiffDetail> queryDeployDiff(List<SceneStruct.DataSource> dataSourceList, List<SceneStruct.DataSource> lastDataSourceList) throws Exception;

    ResponseContent dropdownList(DataSourceQueryParam param);

}
