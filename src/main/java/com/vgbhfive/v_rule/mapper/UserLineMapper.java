package com.vgbhfive.v_rule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.v_rule.entity.UserLineEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/12/3 10:32
 */
@Mapper
public interface UserLineMapper extends BaseMapper<UserLineEntity> {

    List<String> userLineDetails(@Param("userId") Integer userId);

    int batchInsertEntity(@Param("list") List<UserLineEntity> userLineEntityList);

}
