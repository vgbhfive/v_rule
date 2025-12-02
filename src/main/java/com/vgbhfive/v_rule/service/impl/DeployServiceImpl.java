package com.vgbhfive.v_rule.service.impl;

import com.google.gson.Gson;
import com.vgbhfive.v_rule.common.enums.DeployStatusCode;
import com.vgbhfive.v_rule.common.enums.SceneType;
import com.vgbhfive.v_rule.common.enums.ValueType;
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
import com.vgbhfive.v_rule.mapper.*;
import com.vgbhfive.v_rule.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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
    private DivideService divideService;
    @Resource
    private StrategyService strategyService;
    @Resource
    private ProductInterestService productInterestService;
    @Resource
    private RuleSetService ruleSetService;
    @Resource
    private RuleService ruleService;
    @Resource
    private DataSourceService dataSourceService;
    @Resource
    private DataCategoryService dataCategoryService;
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
                deployEntity.setDivideList(params.getDivideList().stream().map(SceneStruct.Divide::getDivideName).collect(Collectors.joining(",")));

                // diff TODO
                deployEntity.setDiff("");

                // 推送core TODO
                deployEntity.setVersion(deployMapper.selectMaxVersion(deployEntity.getLineNo(), deployEntity.getDeployNo()) + 1);
                LineEntity line = lineMapper.selectByLineNo(deployEntity.getLineNo());
                String url = line.getUrl() + "/deploy/scene/setRedis";
                deployEntity.setCoreVersion(url);
                break;
            default:
                return ResponseContent.error("error deploy type!");
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
        Set<String> productInterestNoSet = new HashSet<>();
        Set<String> productPeriodNoSet = new HashSet<>();
        Set<String> productDynamicPeriodNoSet = new HashSet<>();
        Set<String> productLimitNoSet = new HashSet<>();
        Set<String> productDynamicLimitNoSet = new HashSet<>();
        Set<String> productCustomNoSet = new HashSet<>();
        Set<String> ruleNoSet = new HashSet<>(32);
        Set<String> ruleSetNoSet = new HashSet<>(32);
        Set<String> dataSourceNoSet = new HashSet<>(32);
        Set<String> dataCategoryNoSet = new HashSet<>(32);

        SceneStruct.Scene scene = sceneMapper.querySceneBySceneNo(sceneNo);
        if (Objects.isNull(scene)) {
            logger.error("build scene non exists! sceneNo: {}", sceneNo);
            throw new ParamException("build scene non exists!");
        }
        ArrayList<SceneStruct.Scene> sceneList = new ArrayList<SceneStruct.Scene>() {{
            add(scene);
        }};
        SceneStruct.Line line = new SceneStruct.Line(scene.getLineNo());
        List<SceneStruct.Line> lineList = new ArrayList<SceneStruct.Line>() {{
            add(line);
        }};
        List<SceneStruct.Divide> divideList = divideService.queryDivideBySceneNo(sceneNo);
        if (divideList.isEmpty()) {
            logger.error("scene non divide! sceneNo: {}", sceneNo);
            throw new ParamException("scene non divide!");
        }
        divideList.forEach(divide -> {
            strategyNoSet.add(divide.getAccessStrategyNo());
            strategyNoSet.add(divide.getRiskStrategyNo());
            productInterestNoSet.addAll(divide.getProductInterestNoList());
            productPeriodNoSet.addAll(divide.getProductPeriodNoList());
            productDynamicPeriodNoSet.addAll(divide.getProductDynamicPeriodNoList());
            productLimitNoSet.addAll(divide.getProductLimitNoList());
            productDynamicLimitNoSet.addAll(divide.getProductDynamicLimitNoList());
            productCustomNoSet.addAll(divide.getProductCustomNoList());
        });

        List<SceneStruct.Strategy> strategyList = strategyService.queryStrategyByStrategyNos(strategyNoSet);
        List<SceneStruct.ProductInterest> interestList = productInterestService.queryInterestByProductNos(productInterestNoSet);

        strategyList.forEach(strategy -> {
            ruleNoSet.addAll(strategy.getRuleNoList());
            ruleSetNoSet.addAll(strategy.getRuleSetNoList());
        });

        List<SceneStruct.RuleSet> ruleSetList = ruleSetService.queryRuleSetByRuleSetNos(ruleSetNoSet);
        this.getRuleSetList();
        List<SceneStruct.Rule> ruleList = ruleService.queryRuleByRuleNos(ruleNoSet);

        interestList.forEach(interest -> {
            if (interest.getValueType().equals(ValueType.DATASOURCE.getType())) {
                dataSourceNoSet.add(interest.getValue());
            }
        });
        ruleSetList.forEach(ruleSet -> {
            if (ruleSet.getThresholdType().equals(ValueType.DATASOURCE.getType())) {
                dataSourceNoSet.add(ruleSet.getThreshold());
            }
        });
        ruleList.forEach(rule -> {
            dataSourceNoSet.add(rule.getDataSourceNo());
            if (rule.getThresholdType().equals(ValueType.DATASOURCE.getType())) {
                dataSourceNoSet.add(rule.getThreshold());
            }
        });
        List<SceneStruct.DataSource> dataSourceList = dataSourceService.queryDataSourceByDataSourceNos(dataSourceNoSet);
        dataSourceList.forEach(dataSource -> {
            dataCategoryNoSet.add(dataSource.getDataCategoryNo());
        });
        List<SceneStruct.DataCategory> dataCategoryList = dataCategoryService.queryDataCategoryByDataCategoryNos(dataCategoryNoSet);

        return new SceneParams(lineList, sceneList, divideList);
    }

    /**
     * 递归查询所有规则集引用的规则和规则集 TODO
     */
    private void getRuleSetList() {

    }

}
