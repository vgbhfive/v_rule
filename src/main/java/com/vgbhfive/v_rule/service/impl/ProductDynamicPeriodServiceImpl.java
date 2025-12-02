package com.vgbhfive.v_rule.service.impl;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductPeriodEntity;
import com.vgbhfive.v_rule.service.ProductDynamicPeriodService;
import org.springframework.stereotype.Service;

/**
 * @Author vgbhfive
 * @Date 2025/12/2 17:00
 */
@Service
public class ProductDynamicPeriodServiceImpl implements ProductDynamicPeriodService {
    @Override
    public ResponseContent queryList(ProductQueryParam param) {
        return null;
    }

    @Override
    public ResponseContent create(ProductPeriodEntity productPeriodEntity, boolean isUpdate) {
        return null;
    }

    @Override
    public ResponseContent update(ProductPeriodEntity productPeriodEntity) {
        return null;
    }
}
