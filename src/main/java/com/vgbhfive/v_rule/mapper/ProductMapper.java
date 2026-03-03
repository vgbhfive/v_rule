package com.vgbhfive.v_rule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.v_rule.dto.DropdownList;
import com.vgbhfive.v_rule.entity.ProductEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/27 23:14
 */
@Mapper
public interface ProductMapper extends BaseMapper<ProductEntity> {

    Integer updateDeployTimeBatch(@Param("productNos") List<String> productNoList, @Param("deployTime") Date deployTime);

    List<DropdownList> selectDropdownList(@Param("lineNo") String lineNo, @Param("type") String type, @Param("list") List<String> lineNoList);

}
