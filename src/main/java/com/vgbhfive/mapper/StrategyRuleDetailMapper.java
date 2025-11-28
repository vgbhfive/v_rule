package com.vgbhfive.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.entity.StrategyRuleDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 16:32
 */
@Mapper
public interface StrategyRuleDetailMapper extends BaseMapper<StrategyRuleDetailEntity> {

    Integer batchInsertDetails(@Param("list") List<StrategyRuleDetailEntity> detailEntityList);

}
