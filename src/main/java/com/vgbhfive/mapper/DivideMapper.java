package com.vgbhfive.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.dto.divide.DivideListDto;
import com.vgbhfive.dto.divide.DivideQueryParam;
import com.vgbhfive.entity.DivideEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 14:34
 */
@Mapper
public interface DivideMapper extends BaseMapper<DivideEntity> {

    List<DivideListDto> queryList(@Param("param") DivideQueryParam param, @Param("start") Integer start, @Param("limit") Integer limit);

    Integer queryTotalCount(@Param("param") DivideQueryParam param);

}
