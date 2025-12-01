package com.vgbhfive.v_rule.service.impl;

import com.google.gson.Gson;
import com.vgbhfive.v_rule.common.enums.DeployStatusCode;
import com.vgbhfive.v_rule.common.enums.SceneType;
import com.vgbhfive.v_rule.common.exception.ParamException;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.DeployQueryParam;
import com.vgbhfive.v_rule.dto.deploy.SceneParams;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.scene.SceneListDto;
import com.vgbhfive.v_rule.dto.scene.SceneQueryParam;
import com.vgbhfive.v_rule.entity.DeployEntity;
import com.vgbhfive.v_rule.entity.LineEntity;
import com.vgbhfive.v_rule.mapper.DeployMapper;
import com.vgbhfive.v_rule.mapper.DivideMapper;
import com.vgbhfive.v_rule.mapper.LineMapper;
import com.vgbhfive.v_rule.mapper.SceneMapper;
import com.vgbhfive.v_rule.service.DeployService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/29 23:10
 */
@Service
public class DeployServiceImpl implements DeployService {

    private static final Logger logger = LoggerFactory.getLogger(DeployServiceImpl.class);

    @Autowired
    private DeployMapper deployMapper;
    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private LineMapper lineMapper;
    @Autowired
    private DivideMapper divideMapper;
    @Resource
    private Gson gson;

    @Override
    public ResponseContent queryList(DeployQueryParam param) {
        int start = (param.getCurrPage() - 1) * param.getLimit();
        int limit = param.getLimit();

        SceneType type = SceneType.valueOf(param.getDeployType());
        switch (type) {
            case SCENE:
                SceneQueryParam queryParam = new SceneQueryParam();
                queryParam.setLineNo(param.getLineNo());
                queryParam.setSceneName(param.getName());
                queryParam.setSceneNo(param.getNo());
                queryParam.setField(param.getField());
                queryParam.setIsValid(param.getIsValid());

                List<SceneListDto> sceneListDtoList = sceneMapper.queryList(queryParam, start, limit);
                int totalCount = sceneMapper.queryTotalCount(queryParam);

                int totalPage = (totalCount - 1) / limit + 1;
                PageResponse<SceneListDto> result = new PageResponse<>(param.getCurrPage(), limit, totalCount, totalPage, sceneListDtoList);
                return ResponseContent.success(result);
            default:
                return ResponseContent.error("上线场景类型");
        }
    }

    /**
     * 上次上线版本与当前版本的差异
     * @param sceneNo
     * @param sceneType
     * @return
     */
    @Override
    public ResponseContent diff(String sceneNo, String sceneType) {
        return ResponseContent.success(sceneNo + " " + sceneType);
    }

    /**
     * 提交场景部署上线
     * @param deployEntity
     * @return
     */
    @Override
    public ResponseContent pass(DeployEntity deployEntity) {
        if (StringUtils.isBlank(deployEntity.getDeployNo())) {
            return ResponseContent.error("上线场景编码不能为空");
        }
        Integer approvalCount = deployMapper.selectApprovalCount(deployEntity.getDeployNo(), deployEntity.getDeployType());
        if (approvalCount > 0) {
            return ResponseContent.error("当前场景已存在提交内容，请先取消后再次提交");
        }
        Date now = new Date();
        deployEntity.setId(null);
        deployEntity.setIsValid(1);
        deployEntity.setStatusCode(DeployStatusCode.PASS.getCode());
        deployEntity.setDeployAt(now);
        deployEntity.setCreateAt(now);
        deployEntity.setUpdateAt(now);

        SceneType type = SceneType.getInstance(deployEntity.getDeployType());
        switch (type) {
            case SCENE:
                // 构建场景 TODO
                SceneParams params = buildSceneParams(deployEntity.getDeployNo());
                deployEntity.setParams(gson.toJson(params));
                deployEntity.setDivideList("");

                // diff TODO
                deployEntity.setDiff("");

                // 推送core TODO
                deployEntity.setVersion(deployMapper.selectMaxVersion(deployEntity.getLineNo(), deployEntity.getDeployNo()) + 1);
                LineEntity line = lineMapper.selectByLineNo(deployEntity.getLineNo());
                String url = line.getUrl() + "/deploy/scene/setRedis";
                deployEntity.setCoreVersion(url);
                break;
            default:
                return ResponseContent.error("上线场景类型");
        }

        Integer insert = deployMapper.insert(deployEntity);
        if (insert < 1) {
            ResponseContent.success("新增上线部署失败");
        }
        return ResponseContent.success("success");
    }

    /**
     * 构建场景的策略数据
     * @param sceneNo
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public SceneParams buildSceneParams(String sceneNo) {
        Set<String> strategyNoSet = new HashSet<>();
        Set<String> productNoSet = new HashSet<>();
        Set<String> ruleNoSet = new HashSet<>(32);
        Set<String> ruleSetNoSet = new HashSet<>(32);
        Set<String> dataManagerNoSet = new HashSet<>(32);
        Set<String> dataCategoryNoSet = new HashSet<>(32);

        SceneStruct.Scene scene = sceneMapper.querySceneBySceneNo(sceneNo);
        if (Objects.isNull(scene)) {
            logger.error("构建场景不存在:{}", sceneNo);
            throw new ParamException("构建场景不存在");
        }
        ArrayList<SceneStruct.Scene> sceneList = new ArrayList<SceneStruct.Scene>() {{
            add(scene);
        }};
        SceneStruct.Line line = new SceneStruct.Line(scene.getLineNo());
        List<SceneStruct.Line> lineList = new ArrayList<SceneStruct.Line>() {{
            add(line);
        }};
        List<SceneStruct.Divide> divideList = divideMapper.queryDivideBySceneNo(sceneNo);
        if (divideList.isEmpty()) {
            throw new ParamException("场景[" + scene.getSceneName() + "]下没有分流器");
        }
        divideList.forEach(divide -> {

        });

        return null;
    }

}
