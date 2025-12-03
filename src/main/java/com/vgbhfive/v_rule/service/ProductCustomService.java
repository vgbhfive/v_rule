package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.product.ProductCustomListDto;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductCustomEntity;
import com.vgbhfive.v_rule.entity.ProductEntity;
import com.vgbhfive.v_rule.entity.ProductLimitEntity;

import java.util.List;
import java.util.Set;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/27 23:04
 */
public interface ProductCustomService {

    ResponseContent queryList(ProductQueryParam param);

    ResponseContent create(ProductEntity productEntity, boolean isUpdate);

    ResponseContent detail(Integer id);

    ResponseContent update(ProductEntity productEntity);

    List<SceneStruct.ProductCustom> queryCustomByProductNos(Set<String> productNos);

}
