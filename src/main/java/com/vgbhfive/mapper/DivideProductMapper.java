package com.vgbhfive.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.entity.DivideProductEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/29 0:19
 */
@Mapper
public interface DivideProductMapper extends BaseMapper<DivideProductEntity> {

    Integer batchInsert(@Param("list")List<DivideProductEntity> divideProductEntityList);

}
