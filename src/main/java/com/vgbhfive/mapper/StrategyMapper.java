package com.vgbhfive.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.dto.strategy.StrategyListDto;
import com.vgbhfive.dto.strategy.StrategyQueryParam;
import com.vgbhfive.entity.StrategyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 15:02
 */
@Mapper
public interface StrategyMapper extends BaseMapper<StrategyEntity> {

    List<StrategyListDto> queryList(@Param("param") StrategyQueryParam param, @Param("start") Integer start, @Param("limit") Integer limit);

    Integer queryTotalCount(@Param("param") StrategyQueryParam param);

    StrategyEntity queryDetailById(@Param("id") Integer id);

}
