package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.exception.DataBaseException;
import com.vgbhfive.v_rule.common.utils.NoGenerateUtil;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.ruleSet.RuleSetListDto;
import com.vgbhfive.v_rule.dto.ruleSet.RuleSetQueryParam;
import com.vgbhfive.v_rule.entity.RuleSetEntity;
import com.vgbhfive.v_rule.mapper.RuleSetMapper;
import com.vgbhfive.v_rule.service.RuleSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
        int start = (param.getCurrPage() - 1) * param.getLimit();
        int limit = param.getLimit();

        List<RuleSetListDto> ruleSetListDtoList = ruleSetMapper.queryList(param, start, limit);
        int totalCount = ruleSetMapper.queryTotalCount(param);

        int totalPage = (totalCount - 1) / limit + 1;
        PageResponse<RuleSetListDto> result = new PageResponse<>(param.getCurrPage(), limit, totalCount, totalPage, ruleSetListDtoList);
        return ResponseContent.success(result);
    }

    @Override
    public ResponseContent create(RuleSetEntity ruleSetEntity, boolean isUpdate) {
        if (isUpdate) {
            ruleSetEntity.setVersion(ruleSetEntity.getVersion() + 1);
        } else {
            ruleSetEntity.setRuleSetNo(noGenerateUtil.generateNo(Constant.NO_GZJ));
        }
        ruleSetEntity.setId(null);
        Date now = new Date();
        ruleSetEntity.setCreateAt(now);
        ruleSetEntity.setUpdateAt(now);
        Integer insert = ruleSetMapper.insert(ruleSetEntity);
        if (insert < 1) {
            throw new DataBaseException("创建规则集失败");
        }
        return ResponseContent.success();
    }

    @Override
    public ResponseContent update(RuleSetEntity ruleSetEntity) {
        RuleSetEntity oldRuleSetEntity = new RuleSetEntity();
        oldRuleSetEntity.setIsDelete(1);
        oldRuleSetEntity.setUpdateAt(new Date());
        Integer update = ruleSetMapper.update(oldRuleSetEntity,
                new UpdateWrapper<RuleSetEntity>().eq("id", ruleSetEntity.getId()).eq("is_delete", 0));
        if (update < 1) {
            throw new DataBaseException("更新规则集失败");
        }
        return this.create(ruleSetEntity, true);
    }

    @Override
    public List<SceneStruct.RuleSet> queryRuleSetByRuleSetNos(Set<String> ruleSetNoSet) {
        return ruleSetMapper.queryRuleSetByRuleSetNos(ruleSetNoSet);
    }

}
