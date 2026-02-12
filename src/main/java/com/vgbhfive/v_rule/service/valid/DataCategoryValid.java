package com.vgbhfive.v_rule.service.valid;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vgbhfive.v_rule.common.aop.Valid;
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
        List<DataCategoryEntity> objectList = dataCategoryMapper.selectList(new QueryWrapper<DataCategoryEntity>()
                .eq("data_category_name", dataCategoryEntity.getDataCategoryName()).eq("line_no", dataCategoryEntity.getLineNo()).eq("is_delete", 0));
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
    }

}
