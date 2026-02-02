package com.vgbhfive.v_rule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.v_rule.dto.DropdownList;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.strategy.StrategyListDto;
import com.vgbhfive.v_rule.dto.strategy.StrategyQueryParam;
import com.vgbhfive.v_rule.entity.StrategyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 15:02
 */
@Mapper
public interface StrategyMapper extends BaseMapper<StrategyEntity> {

    List<StrategyListDto> queryList(@Param("param") StrategyQueryParam param, @Param("start") Integer start, @Param("limit") Integer limit);

    Integer queryTotalCount(@Param("param") StrategyQueryParam param);

    StrategyEntity queryDetailById(@Param("id") Integer id);

    List<SceneStruct.Strategy> queryStrategyByStrategyNos(@Param("strategyNos") Set<String> strategyNos);

    List<String> queryStrategyDetailRuleNoByStrategyNo(@Param("strategyNo") String strategyNo, @Param("ruleType") String ruleType);

    Integer updateDeployTimeBatch(@Param("strategyList") List<SceneStruct.Strategy> strategyList, @Param("deployTime") Date deployTime);

    List<DropdownList> selectDropdownList(@Param("lineNo") String lineNo);

}
