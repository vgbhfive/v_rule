package com.vgbhfive.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.dto.rule.RuleListDto;
import com.vgbhfive.dto.rule.RuleQueryParam;
import com.vgbhfive.entity.RuleEntity;
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
