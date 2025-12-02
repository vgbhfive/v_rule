package com.vgbhfive.v_rule.service.impl;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductLimitEntity;
import com.vgbhfive.v_rule.service.ProductCustomService;
import org.springframework.stereotype.Service;

/**
 * @Author vgbhfive
 * @Date 2025/12/2 16:59
 */
@Service
public class ProductCustomServiceImpl implements ProductCustomService {
    @Override
    public ResponseContent queryList(ProductQueryParam param) {
        return null;
    }

    @Override
    public ResponseContent create(ProductLimitEntity productLimitEntity, boolean isUpdate) {
        return null;
    }

    @Override
    public ResponseContent update(ProductLimitEntity productLimitEntity) {
        return null;
    }
}
