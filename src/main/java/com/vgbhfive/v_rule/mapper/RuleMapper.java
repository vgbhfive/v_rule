package com.vgbhfive.v_rule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.v_rule.dto.rule.RuleListDto;
import com.vgbhfive.v_rule.dto.rule.RuleQueryParam;
import com.vgbhfive.v_rule.entity.RuleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 16:03
 */
@Mapper
public interface RuleMapper extends BaseMapper<RuleEntity> {

    List<RuleListDto> queryList(@Param("param") RuleQueryParam param, @Param("start") Integer start, @Param("limit") Integer limit);

    Integer queryTotalCount(@Param("param") RuleQueryParam param);

}
