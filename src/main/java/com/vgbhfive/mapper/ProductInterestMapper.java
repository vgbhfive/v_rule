package com.vgbhfive.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.dto.product.ProductInterestListDto;
import com.vgbhfive.dto.product.ProductQueryParam;
import com.vgbhfive.entity.ProductInterestEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/27 23:14
 */
@Mapper
public interface ProductInterestMapper extends BaseMapper<ProductInterestEntity> {

    List<ProductInterestListDto> queryList(@Param("param") ProductQueryParam param, @Param("start") Integer start, @Param("limit") Integer limit);

    Integer queryTotalCount(@Param("param") ProductQueryParam param);

}
