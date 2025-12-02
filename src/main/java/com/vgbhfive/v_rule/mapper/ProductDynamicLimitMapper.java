package com.vgbhfive.v_rule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.v_rule.dto.product.ProductDynamicLimitListDto;
import com.vgbhfive.v_rule.dto.product.ProductLimitListDto;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductDynamicLimitEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/27 23:14
 */
@Mapper
public interface ProductDynamicLimitMapper extends BaseMapper<ProductDynamicLimitEntity> {

    List<ProductDynamicLimitListDto> queryList(@Param("param") ProductQueryParam param, @Param("start") Integer start, @Param("limit") Integer limit);

    Integer queryTotalCount(@Param("param") ProductQueryParam param);

}
