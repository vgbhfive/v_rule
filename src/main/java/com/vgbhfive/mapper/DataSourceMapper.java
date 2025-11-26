package com.vgbhfive.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.dto.datasource.DataSourceListDto;
import com.vgbhfive.dto.datasource.DataSourceQueryParam;
import com.vgbhfive.entity.DataSourceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/11/26 15:41
 */
@Mapper
public interface DataSourceMapper extends BaseMapper<DataSourceEntity> {

    List<DataSourceListDto> queryList(@Param("param") DataSourceQueryParam param, @Param("start") Integer start, @Param("limit") Integer limit);

    Integer queryTotalCount(@Param("param") DataSourceQueryParam param);

}
