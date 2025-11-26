package com.vgbhfive.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.dto.datacategory.DataCategoryListDto;
import com.vgbhfive.dto.datacategory.DataCategoryQueryParam;
import com.vgbhfive.entity.DataCategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/26 21:35
 */
@Mapper
public interface DataCategoryMapper extends BaseMapper<DataCategoryEntity> {

    List<DataCategoryListDto> queryList(@Param("param")DataCategoryQueryParam param, @Param("start") Integer start, @Param("param") Integer limit);

    Integer queryTotalCount(@Param("param")DataCategoryQueryParam param);

}
