package com.vgbhfive.service.impl;

import com.vgbhfive.common.constants.Constant;
import com.vgbhfive.common.exception.DataBaseException;
import com.vgbhfive.common.utils.NoGenerateUtil;
import com.vgbhfive.dto.PageResponse;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.strategy.StrategyListDto;
import com.vgbhfive.dto.strategy.StrategyQueryParam;
import com.vgbhfive.entity.StrategyEntity;
import com.vgbhfive.entity.StrategyRuleDetailEntity;
import com.vgbhfive.mapper.StrategyMapper;
import com.vgbhfive.mapper.StrategyRuleDetailMapper;
import com.vgbhfive.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    @Transactional
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
    @Transactional
    public ResponseContent update(StrategyEntity strategyEntity) {
        return null;
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
