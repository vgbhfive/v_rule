package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.exception.DataBaseException;
import com.vgbhfive.v_rule.common.exception.ParamException;
import com.vgbhfive.v_rule.common.utils.CompareUtil;
import com.vgbhfive.v_rule.common.utils.NoGenerateUtil;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.DetailCompareResult;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.deploy.VersionDiffDetail;
import com.vgbhfive.v_rule.dto.scene.SceneListDto;
import com.vgbhfive.v_rule.dto.scene.SceneQueryParam;
import com.vgbhfive.v_rule.entity.SceneEntity;
import com.vgbhfive.v_rule.mapper.SceneMapper;
import com.vgbhfive.v_rule.service.SceneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
        Date now = new Date();
        if (!isUpdate) {
            sceneEntity.setSceneNo(noGenerateUtil.generateNo(Constant.NO_CJ));
            sceneEntity.setCreateAt(now);
        }
        sceneEntity.setId(null);
        sceneEntity.setIsDelete(0);
        sceneEntity.setUpdateAt(now);
        Integer insert = sceneMapper.insert(sceneEntity);
        if (insert < 1) {
            throw new DataBaseException("创建场景失败");
        }
        return ResponseContent.success(String.format("%s场景成功", isUpdate ? "修改" : "新增"));
    }

    @Override
    public ResponseContent update(SceneEntity sceneEntity) {
        SceneEntity oldSceneEntity = new SceneEntity();
        oldSceneEntity.setIsDelete(1);
        oldSceneEntity.setUpdateAt(new Date());
        int update = sceneMapper.update(oldSceneEntity,
                new UpdateWrapper<SceneEntity>().eq("id", sceneEntity.getId()).eq("is_delete", 0));
        if (update < 1) {
            throw new DataBaseException("更新场景失败");
        }
        return this.create(sceneEntity, true);
    }

    @Override
    public ResponseContent updateValid(Integer id, Integer status) {
        if (Objects.isNull(id)) {
            throw new ParamException("无效参数");
        }
        SceneEntity oldSceneEntity = new SceneEntity();
        oldSceneEntity.setIsValid(status);
        oldSceneEntity.setUpdateAt(new Date());
        int update = sceneMapper.update(oldSceneEntity,
                new UpdateWrapper<SceneEntity>().eq("id", id));
        if (update < 1) {
            throw new DataBaseException("更新场景状态失败");
        }
        return ResponseContent.success("更新场景状态成功");
    }

    @Override
    public List<VersionDiffDetail> queryDeployDiff(List<SceneStruct.Scene> sceneList, List<SceneStruct.Scene> lastSceneList) throws Exception {
        List<VersionDiffDetail> versionDiffDetailList = new ArrayList<>();
        Map<String, SceneStruct.Scene> lastSceneMap = new HashMap<>();
        lastSceneList.forEach(lastScene -> lastSceneMap.put(lastScene.getNo(), lastScene));

        for (SceneStruct.Scene scene : sceneList) {
            List<DetailCompareResult> detailCompareResultList;
            if (lastSceneMap.containsKey(scene.getNo())) {
                SceneStruct.Scene lastScene = lastSceneMap.get(scene.getNo());
                detailCompareResultList = CompareUtil.compare(lastScene, scene, null);
                lastSceneList.remove(lastScene);
            } else {
                detailCompareResultList = CompareUtil.compare(null, scene, null);
            }
            versionDiffDetailList.add(new VersionDiffDetail(scene.getNo(), scene.getName(), detailCompareResultList));
        }

        for (SceneStruct.Scene lastScene : lastSceneList) {
            List<DetailCompareResult> detailCompareResultList = CompareUtil.compare(lastScene, null, null);
            versionDiffDetailList.add(new VersionDiffDetail(lastScene.getNo(), lastScene.getName(), detailCompareResultList));
        }
        return versionDiffDetailList;
    }

    @Override
    public ResponseContent dropdownList() {
        return ResponseContent.success(sceneMapper.selectDropdownList());
    }

}
