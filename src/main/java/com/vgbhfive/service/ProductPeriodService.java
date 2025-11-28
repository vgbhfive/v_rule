package com.vgbhfive.service;

import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.product.ProductQueryParam;
import com.vgbhfive.entity.ProductInterestEntity;
import com.vgbhfive.entity.ProductPeriodEntity;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/27 23:04
 */
public interface ProductPeriodService {

    ResponseContent queryList(ProductQueryParam param);

    ResponseContent create(ProductPeriodEntity productPeriodEntity, boolean isUpdate);

    ResponseContent update(ProductPeriodEntity productPeriodEntity);

}
