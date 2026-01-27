package com.vgbhfive.v_rule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.v_rule.dto.DropdownList;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.scene.SceneListDto;
import com.vgbhfive.v_rule.dto.scene.SceneQueryParam;
import com.vgbhfive.v_rule.entity.SceneEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/23 23:25
 */
@Mapper
public interface SceneMapper extends BaseMapper<SceneEntity> {

    List<SceneListDto> queryList(@Param("param") SceneQueryParam param, @Param("start") Integer start, @Param("limit") Integer limit);

    Integer queryTotalCount(@Param("param") SceneQueryParam param);

    SceneEntity selectBySceneNo(@Param("sceneNo") String sceneNo);

    SceneStruct.Scene querySceneBySceneNo(@Param("sceneNo") String sceneNo);

    List<DropdownList> selectDropdownList();

}
