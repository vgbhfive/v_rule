package com.vgbhfive.v_rule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.v_rule.dto.DropdownList;
import com.vgbhfive.v_rule.dto.user.UserListDto;
import com.vgbhfive.v_rule.dto.user.UserQueryParam;
import com.vgbhfive.v_rule.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/12/3 10:32
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    UserEntity selectByEmail(@Param("email") String email);

    Integer updateByEmail(@Param("entity") UserEntity entity);

    List<UserListDto> queryList(@Param("param") UserQueryParam param,  @Param("start") Integer start, @Param("limit") Integer limit);

    Integer queryTotalCount(@Param("param") UserQueryParam param);

    List<DropdownList> dropdownList();

}
