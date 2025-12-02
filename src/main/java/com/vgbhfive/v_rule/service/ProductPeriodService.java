package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductPeriodEntity;

import java.util.List;
import java.util.Set;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/27 23:04
 */
public interface ProductPeriodService {

    ResponseContent queryList(ProductQueryParam param);

    ResponseContent create(ProductPeriodEntity productPeriodEntity, boolean isUpdate);

    ResponseContent update(ProductPeriodEntity productPeriodEntity);

    List<SceneStruct.ProductPeriod> queryPeriodByProductNos(Set<String> productNos);

}
