package com.vgbhfive.v_rule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.v_rule.entity.DataCategoryDetailEntity;
import com.vgbhfive.v_rule.entity.StrategyRuleDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/26 21:35
 */
@Mapper
public interface DataCategoryDetailMapper extends BaseMapper<DataCategoryDetailEntity> {

    Integer batchInsertDetails(@Param("list") List<DataCategoryDetailEntity> detailEntityList);

}
