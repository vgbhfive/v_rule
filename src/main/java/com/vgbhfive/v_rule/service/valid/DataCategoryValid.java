package com.vgbhfive.v_rule.service.valid;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vgbhfive.v_rule.common.aop.Valid;
import com.vgbhfive.v_rule.common.enums.CategoryType;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.common.exception.ParamException;
import com.vgbhfive.v_rule.entity.DataCategoryEntity;
import com.vgbhfive.v_rule.mapper.DataCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author vgbhfive
 * @Date 2026/2/6 12:17
 */
@Service
@Valid(type = ElementTypes.DATA_CATEGORY)
public class DataCategoryValid implements ValidService<com.vgbhfive.v_rule.entity.DataCategoryEntity> {

    @Autowired
    private DataCategoryMapper dataCategoryMapper;

    @Override
    public void checkParams(DataCategoryEntity dataCategoryEntity, boolean isUpdate) {
        List<DataCategoryEntity> objectList = dataCategoryMapper.selectList(new QueryWrapper<DataCategoryEntity>().
                eq("data_category_name", dataCategoryEntity.getDataCategoryName()).
                eq("line_no", dataCategoryEntity.getLineNo()).
                eq("is_delete", 0));
        if (isUpdate) {
            for (DataCategoryEntity entity : objectList) {
                if (!dataCategoryEntity.getId().equals(entity.getId())) {
                    throw new ParamException("同一业务线下不允许名称相同:" + dataCategoryEntity.getDataCategoryName());
                }
            }
        } else {
            // 新增
            if (Objects.nonNull(objectList) && !objectList.isEmpty()) {
                throw new ParamException("同一业务线下不允许名称相同:" + dataCategoryEntity.getDataCategoryName());
            }
        }

        // 一级来源与二级来源不能包含 [-]
        if (dataCategoryEntity.getSourceFrom().contains("-") || dataCategoryEntity.getSourceType().contains("-")) {
            throw new ParamException("来源中存在非法字符:" + (dataCategoryEntity.getSourceFrom().contains("-") ?
                    dataCategoryEntity.getSourceFrom() : dataCategoryEntity.getSourceType()));
        }

        // Python 数据源分类一级来源必须为【python】
        if (dataCategoryEntity.getCategoryType().equals(CategoryType.PYTHON.getType())) {
            if (!dataCategoryEntity.getSourceFrom().equals(CategoryType.PYTHON.getKey())) {
                throw new ParamException("Python 数据源分类一级来源必须为【python】" + dataCategoryEntity.getSourceFrom());
            }
            dataCategoryEntity.getDetailList().forEach(detail -> {
                if (detail.getValue().length() >= 512) {
                    if (detail.getKey().equals("code")) {
                        throw new ParamException("Python 代码长度最大限制512");
                    } else {
                        throw new ParamException("字段值超出上限！");
                    }
                }
            });
        }

        // HTTP 数据源分类一级来源必须为【http】
        if (dataCategoryEntity.getCategoryType().equals(CategoryType.HTTP.getType())) {
            if (!dataCategoryEntity.getSourceFrom().equals(CategoryType.HTTP.getKey())) {
                throw new ParamException("HTTP 数据源分类一级来源必须为【http】" + dataCategoryEntity.getSourceFrom());
            }
        }
    }

}
