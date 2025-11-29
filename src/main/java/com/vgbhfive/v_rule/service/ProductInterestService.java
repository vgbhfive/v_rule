package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductInterestEntity;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/27 23:04
 */
public interface ProductInterestService {

    ResponseContent queryList(ProductQueryParam param);

    ResponseContent create(ProductInterestEntity productInterestEntity, boolean isUpdate);

    ResponseContent update(ProductInterestEntity productInterestEntity);

}
