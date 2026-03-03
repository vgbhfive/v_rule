package com.vgbhfive.v_rule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.v_rule.dto.DropdownList;
import com.vgbhfive.v_rule.dto.line.LineListDto;
import com.vgbhfive.v_rule.dto.line.LineQueryParam;
import com.vgbhfive.v_rule.entity.LineEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/24 23:29
 */
@Mapper
public interface LineMapper extends BaseMapper<LineEntity> {

    List<LineListDto> queryList(@Param("param") LineQueryParam param, @Param("start") Integer start, @Param("limit") Integer limit);

    Integer queryTotalCount(@Param("param") LineQueryParam param);

    LineEntity selectByLineNo(@Param("lineNo") String lineNo);

    List<DropdownList> selectDropdownList(@Param("list") List<String> lineNoList);

}
