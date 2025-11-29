package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.exception.DataBaseException;
import com.vgbhfive.v_rule.common.utils.NoGenerateUtil;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        if (isUpdate) {
            strategyEntity.setVersion(strategyEntity.getVersion() + 1);
        } else {
            strategyEntity.setStrategyNo(noGenerateUtil.generateNo(Constant.NO_CL));
        }
        List<StrategyRuleDetailEntity> ruleDetailEntityList = buildRuleDetailList(strategyEntity);
        Integer insertDetail = strategyRuleDetailMapper.batchInsertDetails(ruleDetailEntityList);
        if (insertDetail < strategyEntity.getRuleDetailEntityList().size()) {
            throw new DataBaseException("创建策略集失败");
        }
        Date now = new Date();
        strategyEntity.setCreateAt(now);
        strategyEntity.setUpdateAt(now);
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

}
