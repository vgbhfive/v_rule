package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.enums.RuleType;
import com.vgbhfive.v_rule.common.exception.DataBaseException;
import com.vgbhfive.v_rule.common.utils.CompareUtil;
import com.vgbhfive.v_rule.common.utils.NoGenerateUtil;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.DetailCompareResult;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.deploy.VersionDiffDetail;
import com.vgbhfive.v_rule.dto.strategy.StrategyListDto;
import com.vgbhfive.v_rule.dto.strategy.StrategyQueryParam;
import com.vgbhfive.v_rule.entity.StrategyEntity;
import com.vgbhfive.v_rule.entity.StrategyRuleDetailEntity;
import com.vgbhfive.v_rule.mapper.StrategyMapper;
import com.vgbhfive.v_rule.mapper.StrategyRuleDetailMapper;
import com.vgbhfive.v_rule.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 15:01
 */
@Service
public class StrategyServiceImpl implements StrategyService {

    @Autowired
    private StrategyMapper strategyMapper;
    @Autowired
    private StrategyRuleDetailMapper strategyRuleDetailMapper;
    @Resource
    private NoGenerateUtil noGenerateUtil;

    @Override
    public ResponseContent queryList(StrategyQueryParam param) {
        int start = (param.getCurrPage() - 1) * param.getLimit();
        int limit = param.getLimit();

        List<StrategyListDto> strategyListDtoList = strategyMapper.queryList(param, start, limit);
        int totalCount = strategyMapper.queryTotalCount(param);

        int totalPage = (totalCount - 1) / limit + 1;
        PageResponse<StrategyListDto> result = new PageResponse<>(param.getCurrPage(), limit, totalCount, totalPage, strategyListDtoList);
        return ResponseContent.success(result);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResponseContent create(StrategyEntity strategyEntity, boolean isUpdate) {
        Date now = new Date();
        if (isUpdate) {
            strategyEntity.setVersion(strategyEntity.getVersion() + 1);
        } else {
            strategyEntity.setStrategyNo(noGenerateUtil.generateNo(Constant.NO_CL));
            strategyEntity.setCreateAt(now);
        }
        strategyEntity.setId(null);
        strategyEntity.setIsValid(1);
        strategyEntity.setIsDelete(0);
        strategyEntity.setUpdateAt(now);

        List<StrategyRuleDetailEntity> ruleDetailEntityList = buildRuleDetailList(strategyEntity);
        Integer insertDetail = strategyRuleDetailMapper.batchInsertDetails(ruleDetailEntityList);
        if (insertDetail < strategyEntity.getRuleDetailEntityList().size()) {
            throw new DataBaseException("创建策略集失败");
        }
        Integer insert = strategyMapper.insert(strategyEntity);
        if (insert < 1) {
            throw new DataBaseException("创建策略集失败");
        }
        return ResponseContent.success();
    }

    @Override
    public ResponseContent detail(Integer id) {
        return ResponseContent.success(strategyMapper.queryDetailById(id));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResponseContent update(StrategyEntity strategyEntity) {
        StrategyEntity oldStrategyEntity = new StrategyEntity();
        oldStrategyEntity.setIsDelete(1);
        oldStrategyEntity.setUpdateAt(new Date());
        Integer update = strategyMapper.update(oldStrategyEntity,
                new UpdateWrapper<StrategyEntity>().eq("id", strategyEntity.getId()).eq("is_delete", 0));
        if (update < 1) {
            throw new DataBaseException("更新策略集失败");
        }
        StrategyRuleDetailEntity oldStrategyRuleDetailEntity = new StrategyRuleDetailEntity();
        oldStrategyRuleDetailEntity.setIsDelete(1);
        oldStrategyRuleDetailEntity.setUpdateAt(new Date());
        Integer updateInterest = strategyRuleDetailMapper.update(oldStrategyRuleDetailEntity,
                new UpdateWrapper<StrategyRuleDetailEntity>().eq("strategy_no", strategyEntity.getStrategyNo()).eq("is_delete", 0));
        if (updateInterest < strategyEntity.getRuleDetailEntityList().size()) {
            throw new DataBaseException("更新策略集失败");
        }
        return this.create(strategyEntity, true);
    }

    private List<StrategyRuleDetailEntity> buildRuleDetailList(StrategyEntity strategyEntity) {
        List<StrategyRuleDetailEntity> ruleDetailEntityList = new ArrayList<>();
        Date now = new Date();
        strategyEntity.getRuleDetailEntityList().forEach(detailEntity -> {
            detailEntity.setId(null);
            detailEntity.setStrategyNo(strategyEntity.getStrategyNo());
            detailEntity.setVersion(strategyEntity.getVersion());
            detailEntity.setIsDelete(0);
            detailEntity.setCreateAt(now);
            detailEntity.setUpdateAt(now);
            ruleDetailEntityList.add(detailEntity);
        });
        return ruleDetailEntityList;
    }

    @Override
    public List<SceneStruct.Strategy> queryStrategyByStrategyNos(Set<String> strategyNoSet) {
        List<SceneStruct.Strategy> strategyList = strategyMapper.queryStrategyByStrategyNos(strategyNoSet);
        strategyList.forEach(strategy -> {
            strategy.setRuleNoList(strategyMapper.queryStrategyDetailRuleNoByStrategyNo(strategy.getNo(), RuleType.RULE.getKey()));
            strategy.setRuleSetNoList(strategyMapper.queryStrategyDetailRuleNoByStrategyNo(strategy.getNo(), RuleType.RULE_SET.getKey()));
            strategy.setStrategyDetailList(strategyRuleDetailMapper.queryStrategyDetailByStrategyNo(strategy.getNo()));
        });
        return strategyList;
    }

    @Override
    public List<VersionDiffDetail> queryDeployDiff(List<SceneStruct.Strategy> strategyList, List<SceneStruct.Strategy> lastStrategyList) throws Exception {
        List<VersionDiffDetail> versionDiffDetailList = new ArrayList<>();
        Map<String, SceneStruct.Strategy> lastStrategyMap = new HashMap<>();
        lastStrategyList.forEach(strategy -> lastStrategyMap.put(strategy.getNo(), strategy));

        List<String> ignoreList = new ArrayList<>();
        ignoreList.add("version");
        ignoreList.add("isValid");
        for (SceneStruct.Strategy strategy : strategyList) {
            List<DetailCompareResult> detailCompareResultList;
            if (lastStrategyMap.containsKey(strategy.getNo())) {
                SceneStruct.Strategy lastStrategy = lastStrategyMap.get(strategy.getNo());
                detailCompareResultList = CompareUtil.compare(lastStrategy, strategy, ignoreList);
                lastStrategyList.remove(lastStrategy);
            } else {
                detailCompareResultList = CompareUtil.compare(null, strategy, null);
            }
            if (!detailCompareResultList.isEmpty()) {
                versionDiffDetailList.add(new VersionDiffDetail(strategy.getNo(), strategy.getName(), detailCompareResultList));
            }
        }

        for (SceneStruct.Strategy lastStrategy : lastStrategyList) {
            List<DetailCompareResult> detailCompareResultList = CompareUtil.compare(lastStrategy, null, null);
            versionDiffDetailList.add(new VersionDiffDetail(lastStrategy.getNo(), lastStrategy.getName(), detailCompareResultList));
        }
        return versionDiffDetailList;
    }

    @Override
    public ResponseContent updateStrategyDeployTime(List<SceneStruct.Strategy> strategyList, Date deployTime) {
        Integer update = strategyMapper.updateDeployTimeBatch(strategyList, deployTime);
        if (update != strategyList.size()) {
            throw new DataBaseException("更新策略集上线时间失败");
        }
        return ResponseContent.success();
    }

}
