package com.vgbhfive.v_rule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.v_rule.entity.DivideProductEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/29 0:19
 */
@Mapper
public interface DivideProductMapper extends BaseMapper<DivideProductEntity> {

    Integer batchInsert(@Param("list")List<DivideProductEntity> divideProductEntityList);

}
