package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.exception.DataBaseException;
import com.vgbhfive.v_rule.common.utils.NoGenerateUtil;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.line.LineListDto;
import com.vgbhfive.v_rule.dto.line.LineQueryParam;
import com.vgbhfive.v_rule.entity.DataCategoryEntity;
import com.vgbhfive.v_rule.entity.LineEntity;
import com.vgbhfive.v_rule.mapper.DataCategoryMapper;
import com.vgbhfive.v_rule.mapper.LineMapper;
import com.vgbhfive.v_rule.service.LineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/24 23:26
 */
@Service
public class LineServiceImpl implements LineService {

    private static final Logger logger = LoggerFactory.getLogger(LineServiceImpl.class);

    @Autowired
    private LineMapper lineMapper;
    @Autowired
    private DataCategoryMapper dataCategoryMapper;
    @Resource
    private NoGenerateUtil noGenerateUtil;

    @Override
    public ResponseContent queryList(LineQueryParam param) {
        int start = (param.getCurrPage() - 1) * param.getLimit();
        int limit = param.getLimit();

        List<LineListDto> lineListDtoList = lineMapper.queryList(param, start, limit);
        int totalCount = lineMapper.queryTotalCount(param);

        int totalPage = (totalCount - 1) / limit + 1;
        PageResponse<LineListDto> result = new PageResponse<>(param.getCurrPage(), limit, totalCount, totalPage, lineListDtoList);
        return ResponseContent.success(result);
    }

    @Override
    public ResponseContent create(LineEntity lineEntity, boolean isUpdate) {
        Date now = new Date();
        if (!isUpdate) {
            lineEntity.setCreateAt(now);
        }
        lineEntity.setId(null);
        lineEntity.setIsDelete(0);
        lineEntity.setUpdateAt(now);
        Integer insert = lineMapper.insert(lineEntity);
        if (insert < 1) {
            throw new DataBaseException("新增业务线失败");
        }

        // 新增业务线时自动新增固定数据源分类
        autoInsertDataCategory(lineEntity.getLineNo(), "local", "basicInfo", 0);
        autoInsertDataCategory(lineEntity.getLineNo(), "local", "userInfo", 0);
        autoInsertDataCategory(lineEntity.getLineNo(), "local", "orderInfo", 0);
        autoInsertDataCategory(lineEntity.getLineNo(), "local", "deviceInfo", 0);
        autoInsertDataCategory(lineEntity.getLineNo(), "local", "extractInfo", 0);

        return ResponseContent.success(String.format("业务线%s成功", isUpdate ? "修改" : "新增"));
    }

    private void autoInsertDataCategory(String lineNo, String sourceFrom, String sourceType, Integer categoryType) {
        Date now = new Date();
        DataCategoryEntity entity = new DataCategoryEntity();
        entity.setLineNo(lineNo);
        entity.setDataCategoryName(String.format("%s-%s", sourceFrom, sourceType));
        entity.setDataCategoryNo(noGenerateUtil.generateNo(Constant.NO_SJYFL));
        entity.setSourceFrom(sourceFrom);
        entity.setSourceType(sourceType);
        entity.setCategoryType(categoryType);
        entity.setPriority(1);
        entity.setVersion(1);
        entity.setIsValid(1);
        entity.setIsDelete(0);
        entity.setCreateAt(now);
        entity.setUpdateAt(now);
        dataCategoryMapper.insert(entity);
    }

    @Override
    public ResponseContent update(LineEntity lineEntity) {
        LineEntity oldLineEntity = new LineEntity();
        oldLineEntity.setIsDelete(1);
        oldLineEntity.setUpdateAt(new Date());
        Integer update = lineMapper.update(oldLineEntity,
                new UpdateWrapper<LineEntity>().eq("id", lineEntity.getId()).eq("is_delete", 0));
        if (update < 1) {
            throw new DataBaseException("修改业务线失败");
        }
        return this.create(lineEntity, true);
    }

    @Override
    public ResponseContent dropdownList() {
        return ResponseContent.success(lineMapper.selectDropdownList());
    }

}
