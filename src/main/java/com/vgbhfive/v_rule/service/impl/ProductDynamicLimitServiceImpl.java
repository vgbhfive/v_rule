package com.vgbhfive.v_rule.service.impl;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductLimitEntity;
import com.vgbhfive.v_rule.mapper.ProductDynamicLimitMapper;
import com.vgbhfive.v_rule.service.ProductDynamicLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author vgbhfive
 * @Date 2025/12/2 17:00
 */
@Service
public class ProductDynamicLimitServiceImpl implements ProductDynamicLimitService {

    @Autowired
    private ProductDynamicLimitMapper productDynamicLimitMapper;

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

    @Override
    public List<SceneStruct.ProductDynamicLimit> queryDynamicLimitByProductNos(Set<String> productNos) {
        return productDynamicLimitMapper.queryDynamicLimitByProductNos(productNos);
    }

}
