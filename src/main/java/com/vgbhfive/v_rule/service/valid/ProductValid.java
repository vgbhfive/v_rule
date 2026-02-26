package com.vgbhfive.v_rule.service.valid;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vgbhfive.v_rule.common.aop.Valid;
import com.vgbhfive.v_rule.common.enums.ElementTypes;
import com.vgbhfive.v_rule.common.exception.ParamException;
import com.vgbhfive.v_rule.entity.DivideEntity;
import com.vgbhfive.v_rule.entity.ProductEntity;
import com.vgbhfive.v_rule.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author vgbhfive
 * @Date 2026/2/12 17:45
 */
@Service
@Valid(type = ElementTypes.PRODUCT)
public class ProductValid implements ValidService<ProductEntity> {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void checkParams(ProductEntity productEntity, boolean isUpdate) {
        List<ProductEntity> objectList = productMapper.selectList(new QueryWrapper<ProductEntity>()
                .eq("product_name", productEntity.getProductName()).eq("line_no", productEntity.getLineNo()).eq("is_delete", 0));
        if (isUpdate) {
            for (ProductEntity entity : objectList) {
                if (!productEntity.getId().equals(entity.getId())) {
                    throw new ParamException("同一业务线下不允许名称相同:" + productEntity.getProductName());
                }
            }
        } else {
            // 新增
            if (Objects.nonNull(objectList) && !objectList.isEmpty()) {
                throw new ParamException("同一业务线下不允许名称相同:" + productEntity.getProductName());
            }
        }
    }

}
