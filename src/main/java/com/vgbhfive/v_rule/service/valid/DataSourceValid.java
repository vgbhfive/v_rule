package com.vgbhfive.v_rule.service.valid;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vgbhfive.v_rule.common.aop.Valid;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.common.exception.ParamException;
import com.vgbhfive.v_rule.entity.DataSourceEntity;
import com.vgbhfive.v_rule.mapper.DataSourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author vgbhfive
 * @Date 2026/2/6 12:16
 */
@Service
@Valid(type = ElementTypes.DATA_SOURCE)
public class DataSourceValid implements ValidService<DataSourceEntity> {

    @Autowired
    private DataSourceMapper dataSourceMapper;

    @Override
    public void checkParams(DataSourceEntity dataSourceEntity, boolean isUpdate) {
        List<DataSourceEntity> objectList = dataSourceMapper.selectList(new QueryWrapper<DataSourceEntity>()
                .eq("data_source_name", dataSourceEntity.getDataSourceName()).eq("line_no", dataSourceEntity.getLineNo()).eq("is_delete", 0));
        if (isUpdate) {
            for (DataSourceEntity entity : objectList) {
                if (!dataSourceEntity.getId().equals(entity.getId())) {
                    throw new ParamException("同一业务线下不允许名称相同:" + dataSourceEntity.getDataSourceName());
                }
            }
        } else {
            // 新增
            if (Objects.nonNull(objectList) && !objectList.isEmpty()) {
                throw new ParamException("同一业务线下不允许名称相同:" + dataSourceEntity.getDataSourceName());
            }
        }
    }

}
