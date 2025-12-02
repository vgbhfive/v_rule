package com.vgbhfive.v_rule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.product.ProductPeriodListDto;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductPeriodEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/27 23:14
 */
@Mapper
public interface ProductPeriodMapper extends BaseMapper<ProductPeriodEntity> {

    List<ProductPeriodListDto> queryList(@Param("param") ProductQueryParam param, @Param("start") Integer start, @Param("limit") Integer limit);

    Integer queryTotalCount(@Param("param") ProductQueryParam param);

    List<SceneStruct.ProductPeriod> queryPeriodByProductNos(@Param("productNos") Set<String> productNos);

}
