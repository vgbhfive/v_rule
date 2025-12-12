package com.vgbhfive.v_rule.service.impl;

import com.google.gson.Gson;
import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.enums.DeployStatusCode;
import com.vgbhfive.v_rule.common.enums.RuleType;
import com.vgbhfive.v_rule.common.enums.SceneType;
import com.vgbhfive.v_rule.common.enums.ValueType;
import com.vgbhfive.v_rule.common.exception.ParamException;
import com.vgbhfive.v_rule.common.utils.HttpUtil;
import com.vgbhfive.v_rule.common.utils.RedisUtil;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.*;
import com.vgbhfive.v_rule.dto.scene.SceneListDto;
import com.vgbhfive.v_rule.dto.scene.SceneQueryParam;
import com.vgbhfive.v_rule.entity.DeployEntity;
import com.vgbhfive.v_rule.entity.LineEntity;
import com.vgbhfive.v_rule.mapper.DeployMapper;
import com.vgbhfive.v_rule.mapper.LineMapper;
import com.vgbhfive.v_rule.mapper.SceneMapper;
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
    @Resource
    private SceneService sceneService;
    @Resource
    private DivideService divideService;
    @Resource
    private StrategyService strategyService;
    @Resource
    private ProductInterestService productInterestService;
    @Resource
    private ProductPeriodService productPeriodService;
    @Resource
    private ProductLimitService productLimitService;
    @Resource
    private ProductCustomService productCustomService;
    @Resource
    private ProductService productService;
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
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private HttpUtil httpUtil;

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
    public ResponseContent diff(String sceneNo, String sceneType) throws Exception {
        SceneParams sceneParams = buildSceneParams(sceneNo);
        String diffRedisKey = String.format("%s:%s:%s", Constant.REDIS_PREFIX_DEPLOY_DIFF, sceneParams.getLineList().get(0).getLineNo(), sceneNo);

        DeployVersionDiff deployVersionDiff = new DeployVersionDiff();
        DeployEntity lastDeployEntity = deployMapper.queryLastDeploy(sceneNo);
        if (Objects.isNull(lastDeployEntity)) {
            deployVersionDiff.setDeployStatus(1);
            redisUtil.setAndExpire(diffRedisKey, deployVersionDiff);
            return ResponseContent.success("首次上线", deployVersionDiff, DeployVersionDiff.class);
        }

        SceneParams lastSceneParams = gson.fromJson(lastDeployEntity.getParams(), SceneParams.class);
        deployVersionDiff.setDeployStatus(0);
        deployVersionDiff.setScene(sceneService.queryDeployDiff(sceneParams.getSceneList(), lastSceneParams.getSceneList()));
        deployVersionDiff.setDivide(divideService.queryDeployDiff(sceneParams.getDivideList(), lastSceneParams.getDivideList()));
        deployVersionDiff.setStrategy(strategyService.queryDeployDiff(sceneParams.getStrategyList(), lastSceneParams.getStrategyList()));
        deployVersionDiff.setInterest(productInterestService.queryDeployDiff(sceneParams.getInterestList(), lastSceneParams.getInterestList()));
        deployVersionDiff.setPeriod(productPeriodService.queryDeployDiff(sceneParams.getPeriodList(), lastSceneParams.getPeriodList()));
        deployVersionDiff.setLimit(productLimitService.queryDeployDiff(sceneParams.getLimitList(), lastSceneParams.getLimitList()));
        deployVersionDiff.setCustom(productCustomService.queryDeployDiff(sceneParams.getCustomList(), lastSceneParams.getCustomList()));
        deployVersionDiff.setRule(ruleService.queryDeployDiff(sceneParams.getRuleList(), lastSceneParams.getRuleList()));
        deployVersionDiff.setRuleSet(ruleSetService.queryDeployDiff(sceneParams.getRuleSetList(), lastSceneParams.getRuleSetList()));
        deployVersionDiff.setDataSource(dataSourceService.queryDeployDiff(sceneParams.getDataSourceList(), lastSceneParams.getDataSourceList()));
        deployVersionDiff.setDataCategory(dataCategoryService.queryDeployDiff(sceneParams.getDataCategoryList(), lastSceneParams.getDataCategoryList()));

        redisUtil.setAndExpire(diffRedisKey, deployVersionDiff);
        return ResponseContent.success("success", deployVersionDiff, DeployVersionDiff.class);
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
                // build scene
                SceneParams params = buildSceneParams(deployEntity.getDeployNo());
                deployEntity.setField(params.getSceneList().get(0).getField());
                deployEntity.setName(params.getSceneList().get(0).getSceneName());
                deployEntity.setParams(gson.toJson(params));
                deployEntity.setDivideList(params.getDivideList().stream().map(SceneStruct.Divide::getDivideName).collect(Collectors.joining(",")));

                // diff
                String diffRedisKey = String.format("%s:%s:%s", Constant.REDIS_PREFIX_DEPLOY_DIFF, deployEntity.getLineNo(), deployEntity.getDeployNo());
                DeployVersionDiff versionDiff = redisUtil.getObject(diffRedisKey, DeployVersionDiff.class);
                deployEntity.setDiff(gson.toJson(versionDiff));

                // update deployTime
                updateDeployTime(params);

                System.out.println(params);

                // push core
                deployEntity.setVersion(deployMapper.selectMaxVersion(deployEntity.getLineNo(), deployEntity.getDeployNo()) + 1);
                LineEntity line = lineMapper.selectByLineNo(deployEntity.getLineNo());
                DeploySceneStruct deploySceneStruct = new DeploySceneStruct(
                        Constant.CORE_VERSION, buildDeployKv(params, deployEntity.getLineNo(), deployEntity.getField()), deployEntity.getLineNo(), deployEntity.getDeployNo());
                String url = line.getUrl() + "/deploy/scene";
                String resp = httpUtil.post(url, gson.toJson(deploySceneStruct));
                deployEntity.setCoreVersion((String) gson.fromJson(resp, ResponseContent.class).getData());
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
        List<String> divideNoList = new ArrayList<>();
        Set<String> strategyNoSet = new HashSet<>();
        Set<String> productInterestNoSet = new HashSet<>();
        Set<String> productPeriodNoSet = new HashSet<>();
        Set<String> productLimitNoSet = new HashSet<>();
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
            divideNoList.add(divide.getDivideNo());
            strategyNoSet.add(divide.getAccessStrategyNo());
            strategyNoSet.add(divide.getRiskStrategyNo());
            productInterestNoSet.addAll(divide.getProductInterestNoList());
            productPeriodNoSet.addAll(divide.getProductPeriodNoList());
            productLimitNoSet.addAll(divide.getProductLimitNoList());
            productCustomNoSet.addAll(divide.getProductCustomNoList());
        });
        sceneList.get(0).setDivideNoList(divideNoList);

        List<SceneStruct.Strategy> strategyList = strategyService.queryStrategyByStrategyNos(strategyNoSet);
        List<SceneStruct.ProductInterest> interestList = productInterestService.queryInterestByProductNos(productInterestNoSet);
        List<SceneStruct.ProductPeriod> periodList = productPeriodService.queryPeriodByProductNos(productPeriodNoSet);
        List<SceneStruct.ProductLimit> limitList = productLimitService.queryLimitByProductNos(productLimitNoSet);
        List<SceneStruct.ProductCustom> customList = productCustomService.queryCustomByProductNos(productCustomNoSet);

        strategyList.forEach(strategy -> {
            ruleNoSet.addAll(strategy.getRuleNoList());
            ruleSetNoSet.addAll(strategy.getRuleSetNoList());
        });

        List<SceneStruct.RuleSet> ruleSetList = ruleSetService.queryRuleSetByRuleSetNos(ruleSetNoSet);
        this.getRuleSetList(ruleSetList, ruleSetList, ruleNoSet, ruleSetNoSet);
        List<SceneStruct.Rule> ruleList = ruleService.queryRuleByRuleNos(ruleNoSet);

        interestList.forEach(interest -> {
            if (interest.getValueType().equals(ValueType.DATASOURCE.getType())) {
                dataSourceNoSet.add(interest.getValue());
            }
        });
        periodList.forEach(period -> {
            if (period.getValueType().equals(ValueType.DATASOURCE.getType())) {
                dataSourceNoSet.add(period.getValue());
            }
        });
        limitList.forEach(limit -> {
            if (limit.getValueType().equals(ValueType.DATASOURCE.getType())) {
                dataSourceNoSet.add(limit.getValue());
            }
        });
        customList.forEach(custom -> {
            custom.getProductCustomDetailList().forEach(customDetail -> {
                if (customDetail.getValueType().equals(ValueType.DATASOURCE.getType())) {
                    dataSourceNoSet.add(customDetail.getValue());
                }
            });
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

        return new SceneParams(lineList, sceneList, divideList, strategyList, interestList, periodList, limitList, customList,
                ruleSetList, ruleList, dataSourceList, dataCategoryList);
    }

    /**
     * 更新所有上线的deployTime
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    private void updateDeployTime(SceneParams params) {
        Date now = new Date();
        if (!params.getRuleList().isEmpty()) {
            ruleService.updateRuleDeployTime(params.getRuleList(), now);
        }
        if (!params.getRuleSetList().isEmpty()) {
            ruleSetService.updateRuleSetDeployTime(params.getRuleSetList(), now);
        }
        List<String> productNos = new ArrayList<>();
        if (!params.getInterestList().isEmpty()) {
            productNos.addAll(params.getInterestList().stream().map(SceneStruct.ProductInterest::getProductNo).collect(Collectors.toList()));
        }
        if (!params.getPeriodList().isEmpty()) {
            productNos.addAll(params.getPeriodList().stream().map(SceneStruct.ProductPeriod::getProductNo).collect(Collectors.toList()));
        }
        if (!params.getLimitList().isEmpty()) {
            productNos.addAll(params.getLimitList().stream().map(SceneStruct.ProductLimit::getProductNo).collect(Collectors.toList()));
        }
        if (!params.getCustomList().isEmpty()) {
            productNos.addAll(params.getCustomList().stream().map(SceneStruct.ProductCustom::getProductNo).collect(Collectors.toList()));
        }
        productService.updateProductDeployTime(productNos, now);
        if (!params.getStrategyList().isEmpty()) {
            strategyService.updateStrategyDeployTime(params.getStrategyList(), now);
        }
        if (!params.getDivideList().isEmpty()) {
            divideService.updateDivideDeployTime(params.getDivideList(), now);
        }
    }

    /**
     * 构建SceneParams 的kv 键值对
     * @param params 场景
     * @param lineNo 业务线编码
     * @param field 场景字段
     *  @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    private Map<String, String> buildDeployKv(SceneParams params, String lineNo, String field) {
        String corePrefix = Constant.CORE_PREFIX;
        Map<String, String> kv = new HashMap<>();
        params.getSceneList().forEach(scene -> {
            String key = String.format(corePrefix, lineNo, field, "scene", scene.getField());
            kv.put(key, gson.toJson(scene));
        });
        params.getDivideList().forEach(divide -> {
            String key = String.format(corePrefix, lineNo, field, "divide", divide.getDivideNo());
            kv.put(key, gson.toJson(divide));
        });
        params.getStrategyList().forEach(strategy -> {
            String key = String.format(corePrefix, lineNo, field, "strategy", strategy.getStrategyNo());
            kv.put(key, gson.toJson(strategy));
        });
        params.getInterestList().forEach(interest -> {
            String key = String.format(corePrefix, lineNo, field, "interest", interest.getProductNo());
            kv.put(key, gson.toJson(interest));
        });
        params.getPeriodList().forEach(period -> {
            String key = String.format(corePrefix, lineNo, field, "period", period.getProductNo());
            kv.put(key, gson.toJson(period));
        });
        params.getLimitList().forEach(limit -> {
            String key = String.format(corePrefix, lineNo, field, "limit", limit.getProductNo());
            kv.put(key, gson.toJson(limit));
        });
        params.getCustomList().forEach(custom -> {
            String key = String.format(corePrefix, lineNo, field, "custom", custom.getProductNo());
            kv.put(key, gson.toJson(custom));
        });
        params.getRuleSetList().forEach(ruleSet -> {
            String key = String.format(corePrefix, lineNo, field, "ruleSet", ruleSet.getRuleSetNo());
            kv.put(key, gson.toJson(ruleSet));
        });
        params.getRuleList().forEach(rule -> {
            String key = String.format(corePrefix, lineNo, field, "rule", rule.getRuleNo());
            kv.put(key, gson.toJson(rule));
        });
        params.getDataSourceList().forEach(dataSource -> {
            String key = String.format(corePrefix, lineNo, field, "dataSource", dataSource.getDataSourceNo());
            kv.put(key, gson.toJson(dataSource));
        });
        params.getDataCategoryList().forEach(dataCategory -> {
            String key = String.format(corePrefix, lineNo, field, "dataCategory", dataCategory.getDataCategoryNo());
            kv.put(key, gson.toJson(dataCategory));
        });
        return kv;
    }

    /**
     * 递归查询所有规则集引用的规则和规则集
     * @param ruleSetList 汇总所有的ruleSet
     * @param tmpRuleSetList 本次查询的ruleSet
     * @param ruleNoSet 汇总所有的ruleNoSet
     * @param ruleSetNoSet 汇总所有的ruleSetNoSet
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    private void getRuleSetList(List<SceneStruct.RuleSet> ruleSetList, List<SceneStruct.RuleSet> tmpRuleSetList,
                                Set<String> ruleNoSet, Set<String> ruleSetNoSet) {
        Set<String> tmpRuleSetNoSet = new HashSet<>();
        tmpRuleSetList.forEach(ruleSet -> {
            String no1 = ruleSet.getFirstNo();
            String type1 = ruleSet.getFirstType();
            if (type1.equals(RuleType.RULE.getKey())) {
                ruleNoSet.add(no1);
            }
            if (type1.equals(RuleType.RULE_SET.getKey()) && !ruleSetNoSet.contains(no1)) {
                ruleSetNoSet.add(no1);
                tmpRuleSetNoSet.add(no1);
            }
            String no2 = ruleSet.getSecondNo();
            String type2 = ruleSet.getSecondType();
            if (type2.equals(RuleType.RULE.getKey())) {
                ruleNoSet.add(no2);
            }
            if (type2.equals(RuleType.RULE_SET.getKey()) && !ruleSetNoSet.contains(no1)) {
                ruleSetNoSet.add(no2);
                tmpRuleSetNoSet.add(no2);
            }
        });
        if (!tmpRuleSetNoSet.isEmpty()) {
            List<SceneStruct.RuleSet> nextRuleSetList = ruleSetService.queryRuleSetByRuleSetNos(tmpRuleSetNoSet);
            ruleSetList.addAll(nextRuleSetList);
            getRuleSetList(ruleSetList, nextRuleSetList, ruleNoSet, ruleSetNoSet);
        }
    }

    @Override
    public ResponseContent rollback() {
        return null;
    }

}
