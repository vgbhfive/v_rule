package com.vgbhfive.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.dto.ruleSet.RuleSetListDto;
import com.vgbhfive.dto.ruleSet.RuleSetQueryParam;
import com.vgbhfive.entity.RuleSetEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 16:03
 */
@Mapper
public interface RuleSetMapper extends BaseMapper<RuleSetEntity> {

    List<RuleSetListDto> queryList(@Param("param") RuleSetQueryParam param, @Param("start") Integer start, @Param("limit") Integer limit);

    Integer queryTotalCount(@Param("param") RuleSetQueryParam param);

}
