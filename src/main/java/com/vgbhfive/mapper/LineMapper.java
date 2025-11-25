package com.vgbhfive.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.dto.line.LineListDto;
import com.vgbhfive.dto.line.LineQueryParam;
import com.vgbhfive.entity.LineEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/24 23:29
 */
@Mapper
public interface LineMapper extends BaseMapper<LineEntity> {

    List<LineListDto> queryList(@Param("param") LineQueryParam param, @Param("start") Integer start, @Param("limit") Integer limit);

    Integer queryTotalCount(@Param("param") LineQueryParam param);

}
