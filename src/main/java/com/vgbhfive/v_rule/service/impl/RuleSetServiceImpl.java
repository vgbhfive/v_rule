package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.exception.DataBaseException;
import com.vgbhfive.v_rule.common.exception.ParamException;
import com.vgbhfive.v_rule.common.utils.CompareUtil;
import com.vgbhfive.v_rule.common.utils.NoGenerateUtil;
import com.vgbhfive.v_rule.common.utils.RequestHolder;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.DetailCompareResult;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.deploy.VersionDiffDetail;
import com.vgbhfive.v_rule.dto.ruleSet.RuleSetListDto;
import com.vgbhfive.v_rule.dto.ruleSet.RuleSetQueryParam;
import com.vgbhfive.v_rule.entity.RuleSetEntity;
import com.vgbhfive.v_rule.mapper.RuleSetMapper;
import com.vgbhfive.v_rule.service.RuleSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author vgbhfive
 * @Date 2025/11/27 16:44
 */
@Service
public class RuleSetServiceImpl implements RuleSetService {

    @Autowired
    private RuleSetMapper ruleSetMapper;
    @Resource
    private NoGenerateUtil noGenerateUtil;

    @Override
    public ResponseContent queryList(RuleSetQueryParam param) {
        List<String> lineList = (List<String>) RequestHolder.get().get(Constant.LINE_PERMISSION_SET);
        param.setLineNoList(lineList);
        int start = (param.getCurrPage() - 1) * param.getLimit();
        int limit = param.getLimit();

        List<RuleSetListDto> ruleSetListDtoList = Objects.isNull(lineList) || !lineList.isEmpty() ?
                ruleSetMapper.queryList(param, start, limit) : new ArrayList<>();
        int totalCount = Objects.isNull(lineList) || !lineList.isEmpty() ?
                ruleSetMapper.queryTotalCount(param) : 0;

        int totalPage = (totalCount - 1) / limit + 1;
        PageResponse<RuleSetListDto> result = new PageResponse<>(param.getCurrPage(), limit, totalCount, totalPage, ruleSetListDtoList);
        return ResponseContent.success(result);
    }

    @Override
    public ResponseContent create(RuleSetEntity ruleSetEntity, boolean isUpdate) {
        Date now = new Date();
        if (isUpdate) {
            ruleSetEntity.setVersion(ruleSetEntity.getVersion() + 1);
        } else {
            ruleSetEntity.setRuleSetNo(noGenerateUtil.generateNo(Constant.NO_GZJ));
            ruleSetEntity.setVersion(1);
            ruleSetEntity.setCreateAt(now);
        }
        ruleSetEntity.setId(null);
        ruleSetEntity.setIsDelete(0);
        ruleSetEntity.setUpdateAt(now);
        int insert = ruleSetMapper.insert(ruleSetEntity);
        if (insert < 1) {
            throw new DataBaseException("创建规则集失败");
        }
        return ResponseContent.success(String.format("%s规则集成功", isUpdate ? "修改" : "新增"));
    }

    @Override
    public ResponseContent update(RuleSetEntity ruleSetEntity) {
        RuleSetEntity oldRuleSetEntity = new RuleSetEntity();
        oldRuleSetEntity.setIsDelete(1);
        oldRuleSetEntity.setUpdateAt(new Date());
        int update = ruleSetMapper.update(oldRuleSetEntity,
                new UpdateWrapper<RuleSetEntity>().eq("id", ruleSetEntity.getId()).eq("is_delete", 0));
        if (update < 1) {
            throw new DataBaseException("更新规则集失败");
        }
        return this.create(ruleSetEntity, true);
    }

    @Override
    public ResponseContent updateValid(Integer id, Integer status) {
        if (Objects.isNull(id)) {
            throw new ParamException("无效参数");
        }
        RuleSetEntity oldRuleSetEntity = new RuleSetEntity();
        oldRuleSetEntity.setIsValid(status);
        oldRuleSetEntity.setUpdateAt(new Date());
        int update = ruleSetMapper.update(oldRuleSetEntity,
                new UpdateWrapper<RuleSetEntity>().eq("id", id));
        if (update < 1) {
            throw new DataBaseException("更新规则集状态失败");
        }
        return ResponseContent.success("更新规则集状态成功");
    }

    @Override
    public List<SceneStruct.RuleSet> queryRuleSetByRuleSetNos(Set<String> ruleSetNoSet) {
        if (ruleSetNoSet.isEmpty()) {
            return new ArrayList<>();
        }
        return ruleSetMapper.queryRuleSetByRuleSetNos(ruleSetNoSet);
    }

    @Override
    public List<VersionDiffDetail> queryDeployDiff(List<SceneStruct.RuleSet> ruleSetList, List<SceneStruct.RuleSet> lastRuleSetList) throws Exception {
        List<VersionDiffDetail> versionDiffDetailList = new ArrayList<>();
        Map<String, SceneStruct.RuleSet> lastRuleSetMap = new HashMap<>();
        lastRuleSetList.forEach(lastRuleSet -> lastRuleSetMap.put(lastRuleSet.getNo(), lastRuleSet));

        List<String> ignoreList = new ArrayList<>();
        ignoreList.add("version");
        ignoreList.add("isValid");
        for (SceneStruct.RuleSet ruleSet : ruleSetList) {
            List<DetailCompareResult> detailCompareResultList;
            if (lastRuleSetMap.containsKey(ruleSet.getNo())) {
                SceneStruct.RuleSet lastRuleSet = lastRuleSetMap.get(ruleSet.getNo());
                detailCompareResultList = CompareUtil.compare(lastRuleSet, ruleSet, ignoreList);
                lastRuleSetList.remove(lastRuleSet);
            } else {
                detailCompareResultList = CompareUtil.compare(null, ruleSet, null);
            }
            if (!detailCompareResultList.isEmpty()) {
                versionDiffDetailList.add(new VersionDiffDetail(ruleSet.getNo(), ruleSet.getName(), detailCompareResultList));
            }
        }

        for (SceneStruct.RuleSet lastRuleSet : lastRuleSetList) {
            List<DetailCompareResult> detailCompareResultList = CompareUtil.compare(lastRuleSet, null, null);
            versionDiffDetailList.add(new VersionDiffDetail(lastRuleSet.getNo(), lastRuleSet.getName(), detailCompareResultList));
        }
        return versionDiffDetailList;
    }

    @Override
    public ResponseContent updateRuleSetDeployTime(List<SceneStruct.RuleSet> ruleSetList, Date deployTime) {
        Integer update = ruleSetMapper.updateDeployTimeBatch(ruleSetList, deployTime);
        if (update != ruleSetList.size()) {
            throw new DataBaseException("更新规则集上线时间异常");
        }
        return ResponseContent.success();
    }

    @Override
    public ResponseContent dropdownList(RuleSetQueryParam param) {
        List<String> lineList = (List<String>) RequestHolder.get().get(Constant.LINE_PERMISSION_SET);
        return ResponseContent.success(ruleSetMapper.selectDropdownList(param.getLineNo(), lineList));
    }

}
