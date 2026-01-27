package com.vgbhfive.v_rule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.v_rule.dto.DropdownList;
import com.vgbhfive.v_rule.dto.datasource.DataSourceListDto;
import com.vgbhfive.v_rule.dto.datasource.DataSourceQueryParam;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.entity.DataSourceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @Author vgbhfive
 * @Date 2025/11/26 15:41
 */
@Mapper
public interface DataSourceMapper extends BaseMapper<DataSourceEntity> {

    List<DataSourceListDto> queryList(@Param("param") DataSourceQueryParam param, @Param("start") Integer start, @Param("limit") Integer limit);

    Integer queryTotalCount(@Param("param") DataSourceQueryParam param);

    List<SceneStruct.DataSource> queryDataSourceByDataSourceNos(@Param("dataSourceNos") Set<String> dataSourceNos);

    List<DropdownList> selectDropdownList();

}
