package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.exception.DataBaseException;
import com.vgbhfive.v_rule.common.utils.CompareUtil;
import com.vgbhfive.v_rule.common.utils.NoGenerateUtil;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.DetailCompareResult;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.deploy.VersionDiffDetail;
import com.vgbhfive.v_rule.dto.rule.RuleListDto;
import com.vgbhfive.v_rule.dto.rule.RuleQueryParam;
import com.vgbhfive.v_rule.entity.RuleEntity;
import com.vgbhfive.v_rule.mapper.RuleMapper;
import com.vgbhfive.v_rule.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 16:03
 */
@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleMapper ruleMapper;
    @Resource
    private NoGenerateUtil noGenerateUtil;

    @Override
    public ResponseContent queryList(RuleQueryParam param) {
        int start = (param.getCurrPage() - 1) * param.getLimit();
        int limit = param.getLimit();

        List<RuleListDto> ruleListDtoList = ruleMapper.queryList(param, start, limit);
        int totalCount = ruleMapper.queryTotalCount(param);

        int totalPage = (totalCount - 1) / limit + 1;
        PageResponse<RuleListDto> result = new PageResponse<>(param.getCurrPage(), limit, totalCount, totalPage, ruleListDtoList);
        return ResponseContent.success(result);
    }

    @Override
    public ResponseContent create(RuleEntity ruleEntity, boolean isUpdate) {
        Date now = new Date();
        if (isUpdate) {
            ruleEntity.setVersion(ruleEntity.getVersion() + 1);
        } else {
            ruleEntity.setRuleNo(noGenerateUtil.generateNo(Constant.NO_GZ));
            ruleEntity.setVersion(1);
            ruleEntity.setCreateAt(now);
        }
        ruleEntity.setId(null);
        ruleEntity.setIsValid(1);
        ruleEntity.setIsDelete(0);
        ruleEntity.setUpdateAt(now);
        Integer insert = ruleMapper.insert(ruleEntity);
        if (insert < 1) {
            throw new DataBaseException("创建规则失败");
        }
        return ResponseContent.success();
    }

    @Override
    public ResponseContent update(RuleEntity ruleEntity) {
        RuleEntity oldRuleEntity = new RuleEntity();
        oldRuleEntity.setIsDelete(1);
        oldRuleEntity.setUpdateAt(new Date());
        Integer update = ruleMapper.update(oldRuleEntity,
                new UpdateWrapper<RuleEntity>().eq("id", ruleEntity.getId()).eq("is_delete", 0));
        if (update < 1) {
            throw new DataBaseException("更新规则失败");
        }
        return this.create(ruleEntity, true);
    }

    @Override
    public List<SceneStruct.Rule> queryRuleByRuleNos(Set<String> ruleNoSet) {
        if (ruleNoSet.isEmpty()) {
            return new ArrayList<>();
        }
        return ruleMapper.queryRuleByRuleNos(ruleNoSet);
    }

    @Override
    public List<VersionDiffDetail> queryDeployDiff(List<SceneStruct.Rule> ruleList, List<SceneStruct.Rule> lastRuleList) throws Exception {
        List<VersionDiffDetail> versionDiffDetailList = new ArrayList<>();
        Map<String, SceneStruct.Rule> lastRuleMap = new HashMap<>();
        lastRuleList.forEach(lastRule -> lastRuleMap.put(lastRule.getNo(), lastRule));

        List<String> ignoreList = new ArrayList<>();
        ignoreList.add("version");
        ignoreList.add("isValid");
        for (SceneStruct.Rule rule : ruleList) {
            List<DetailCompareResult> detailCompareResultList;
            if (lastRuleMap.containsKey(rule.getNo())) {
                SceneStruct.Rule lastRule = lastRuleMap.get(rule.getNo());
                detailCompareResultList = CompareUtil.compare(lastRule, rule, ignoreList);
                lastRuleList.remove(lastRule);
            } else {
                detailCompareResultList = CompareUtil.compare(null, rule, null);
            }
            if (!detailCompareResultList.isEmpty()) {
                versionDiffDetailList.add(new VersionDiffDetail(rule.getNo(), rule.getName(), detailCompareResultList));
            }
        }

        for (SceneStruct.Rule lastRule : lastRuleList) {
            List<DetailCompareResult> detailCompareResultList = CompareUtil.compare(lastRule, null, null);
            versionDiffDetailList.add(new VersionDiffDetail(lastRule.getNo(), lastRule.getName(), detailCompareResultList));
        }
        return versionDiffDetailList;
    }

    @Override
    public ResponseContent updateRuleDeployTime(List<SceneStruct.Rule> ruleList, Date deployTime) {
        Integer update = ruleMapper.updateDeployTimeBatch(ruleList, deployTime);
        if (update != ruleList.size()) {
            throw new DataBaseException("更新规则上线时间异常");
        }
        return ResponseContent.success();
    }

}
