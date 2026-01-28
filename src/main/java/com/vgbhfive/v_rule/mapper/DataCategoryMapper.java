package com.vgbhfive.v_rule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.v_rule.dto.DropdownList;
import com.vgbhfive.v_rule.dto.datacategory.DataCategoryListDto;
import com.vgbhfive.v_rule.dto.datacategory.DataCategoryQueryParam;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.entity.DataCategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/26 21:35
 */
@Mapper
public interface DataCategoryMapper extends BaseMapper<DataCategoryEntity> {

    List<DataCategoryListDto> queryList(@Param("param") DataCategoryQueryParam param, @Param("start") Integer start, @Param("limit") Integer limit);

    Integer queryTotalCount(@Param("param") DataCategoryQueryParam param);

    List<SceneStruct.DataCategory> queryDataCategoryByDataCategoryNos(@Param("dataCategoryNos") Set<String> dataCategoryNos);

    List<DropdownList> selectDropdownList(@Param("lineNo") String lineNo);

}
