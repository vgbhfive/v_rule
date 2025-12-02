package com.vgbhfive.v_rule.service.impl;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductPeriodEntity;
import com.vgbhfive.v_rule.mapper.ProductDynamicPeriodMapper;
import com.vgbhfive.v_rule.service.ProductDynamicPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @Author vgbhfive
 * @Date 2025/12/2 17:00
 */
@Service
public class ProductDynamicPeriodServiceImpl implements ProductDynamicPeriodService {

    @Autowired
    private ProductDynamicPeriodMapper productDynamicPeriodMapper;

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

    @Override
    public List<SceneStruct.ProductDynamicPeriod> queryDynamicPeriodByProductNos(Set<String> productNos) {
        return productDynamicPeriodMapper.queryDynamicPeriodByProductNos(productNos);
    }

}
