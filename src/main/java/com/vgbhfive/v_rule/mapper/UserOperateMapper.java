package com.vgbhfive.v_rule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.v_rule.entity.UserOperateEntity;
import com.vgbhfive.v_rule.entity.pub.OperatePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/12/3 10:32
 */
@Mapper
public interface UserOperateMapper extends BaseMapper<UserOperateEntity> {

    List<String> queryPermission(@Param("userId") Integer userId, @Param("type") Integer type);

    List<OperatePermission> queryUniqueSignByUserId(@Param("userId") Integer userId);

    List<Integer> userOperateDetails(@Param("userId") Integer userId);

    Integer batchInsertEntity(@Param("list") List<UserOperateEntity> userOperateEntityList);

}
