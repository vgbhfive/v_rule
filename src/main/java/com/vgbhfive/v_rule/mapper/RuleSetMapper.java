package com.vgbhfive.v_rule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.v_rule.dto.DropdownList;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.ruleSet.RuleSetListDto;
import com.vgbhfive.v_rule.dto.ruleSet.RuleSetQueryParam;
import com.vgbhfive.v_rule.entity.RuleSetEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 16:03
 */
@Mapper
public interface RuleSetMapper extends BaseMapper<RuleSetEntity> {

    List<RuleSetListDto> queryList(@Param("param") RuleSetQueryParam param, @Param("start") Integer start, @Param("limit") Integer limit);

    Integer queryTotalCount(@Param("param") RuleSetQueryParam param);

    List<SceneStruct.RuleSet> queryRuleSetByRuleSetNos(@Param("ruleSetNos") Set<String> ruleSetNos);

    Integer updateDeployTimeBatch(@Param("ruleSetList") List<SceneStruct.RuleSet> ruleSetList, @Param("deployTime") Date deployTime);

    List<DropdownList> selectDropdownList();

}
