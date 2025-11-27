package com.vgbhfive.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.common.constants.Constant;
import com.vgbhfive.common.exception.DataBaseException;
import com.vgbhfive.common.utils.NoGenerateUtil;
import com.vgbhfive.dto.PageResponse;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.line.LineListDto;
import com.vgbhfive.dto.rule.RuleListDto;
import com.vgbhfive.dto.rule.RuleQueryParam;
import com.vgbhfive.entity.DataSourceEntity;
import com.vgbhfive.entity.RuleEntity;
import com.vgbhfive.mapper.RuleMapper;
import com.vgbhfive.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
        if (isUpdate) {
            ruleEntity.setVersion(ruleEntity.getVersion() + 1);
        } else {
            ruleEntity.setRuleNo(noGenerateUtil.generateNo(Constant.NO_GZ));
        }
        ruleEntity.setId(null);
        Date now = new Date();
        ruleEntity.setCreateAt(now);
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
}
