package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.exception.DataBaseException;
import com.vgbhfive.v_rule.common.utils.NoGenerateUtil;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.scene.SceneListDto;
import com.vgbhfive.v_rule.dto.scene.SceneQueryParam;
import com.vgbhfive.v_rule.entity.SceneEntity;
import com.vgbhfive.v_rule.mapper.SceneMapper;
import com.vgbhfive.v_rule.service.SceneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/23 23:16
 */
@Service
public class SceneServiceImpl implements SceneService {

    private static final Logger logger = LoggerFactory.getLogger(SceneServiceImpl.class);

    @Resource
    private SceneMapper sceneMapper;
    @Resource
    private NoGenerateUtil noGenerateUtil;

    @Override
    public ResponseContent queryList(SceneQueryParam param) {
        int start = (param.getCurrPage() - 1) * param.getLimit();
        int limit = param.getLimit();

        List<SceneListDto> sceneListDtoList = sceneMapper.queryList(param, start, limit);
        int totalCount = sceneMapper.queryTotalCount(param);

        int totalPage = (totalCount - 1) / limit + 1;
        PageResponse<SceneListDto> result = new PageResponse<>(param.getCurrPage(), limit, totalCount, totalPage, sceneListDtoList);
        return ResponseContent.success(result);
    }

    @Override
    public ResponseContent create(SceneEntity sceneEntity, boolean isUpdate) {
        if (!isUpdate) {
            sceneEntity.setSceneNo(noGenerateUtil.generateNo(Constant.NO_CJ));
        }
        sceneEntity.setId(null);
        Date now = new Date();
        sceneEntity.setCreateAt(now);
        sceneEntity.setUpdateAt(now);
        Integer insert = sceneMapper.insert(sceneEntity);
        if (insert < 1) {
            throw new DataBaseException("创建场景失败");
        }
        return ResponseContent.success();
    }

    @Override
    public ResponseContent update(SceneEntity sceneEntity) {
        SceneEntity oldLineEntity = new SceneEntity();
        oldLineEntity.setIsDelete(1);
        oldLineEntity.setUpdateAt(new Date());
        Integer update = sceneMapper.update(oldLineEntity,
                new UpdateWrapper<SceneEntity>().eq("id", sceneEntity.getId()).eq("is_delete", 0));
        if (update < 1) {
            throw new DataBaseException("更新场景失败");
        }
        return this.create(sceneEntity, true);
    }
}
