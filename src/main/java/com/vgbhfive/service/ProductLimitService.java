package com.vgbhfive.service;

import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.product.ProductQueryParam;
import com.vgbhfive.entity.ProductLimitEntity;
import com.vgbhfive.entity.ProductPeriodEntity;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/27 23:04
 */
public interface ProductLimitService {

    ResponseContent queryList(ProductQueryParam param);

    ResponseContent create(ProductLimitEntity productLimitEntity, boolean isUpdate);

    ResponseContent update(ProductLimitEntity productLimitEntity);

}
