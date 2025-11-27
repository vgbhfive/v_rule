package com.vgbhfive.service;

import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.product.ProductQueryParam;
import com.vgbhfive.entity.ProductInterestEntity;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/27 23:04
 */
public interface ProductInterestService {

    ResponseContent queryList(ProductQueryParam param);

    ResponseContent create(ProductInterestEntity productInterestEntity, boolean isUpdate);

    ResponseContent update(ProductInterestEntity productInterestEntity);

}
