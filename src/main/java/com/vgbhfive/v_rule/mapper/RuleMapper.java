package com.vgbhfive.v_rule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.v_rule.dto.DropdownList;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.rule.RuleListDto;
import com.vgbhfive.v_rule.dto.rule.RuleQueryParam;
import com.vgbhfive.v_rule.entity.RuleEntity;
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
public interface RuleMapper extends BaseMapper<RuleEntity> {

    List<RuleListDto> queryList(@Param("param") RuleQueryParam param, @Param("start") Integer start, @Param("limit") Integer limit);

    Integer queryTotalCount(@Param("param") RuleQueryParam param);

    List<SceneStruct.Rule> queryRuleByRuleNos(@Param("ruleNos") Set<String> ruleNos);

    Integer updateDeployTimeBatch(@Param("ruleList") List<SceneStruct.Rule> ruleList, @Param("deployTime") Date deployTime);

    List<DropdownList> selectDropdownList();

}
