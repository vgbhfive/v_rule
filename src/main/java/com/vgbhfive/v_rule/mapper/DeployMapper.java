package com.vgbhfive.v_rule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vgbhfive.v_rule.entity.DeployEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/30 13:24
 */
@Mapper
public interface DeployMapper extends BaseMapper<DeployEntity> {

    Integer selectApprovalCount(@Param("no") String no, @Param("type") String type);

    Integer selectMaxVersion(@Param("lineNo") String lineNo, @Param("no") String no);

    DeployEntity queryLastDeploy(@Param("sceneNo") String sceneNo);

}
