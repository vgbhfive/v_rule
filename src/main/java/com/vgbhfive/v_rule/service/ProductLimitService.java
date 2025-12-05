package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.deploy.VersionDiffDetail;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductLimitEntity;

import java.util.List;
import java.util.Set;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/27 23:04
 */
public interface ProductLimitService {

    ResponseContent queryList(ProductQueryParam param);

    ResponseContent create(ProductLimitEntity productLimitEntity, boolean isUpdate);

    ResponseContent update(ProductLimitEntity productLimitEntity);

    List<SceneStruct.ProductLimit> queryLimitByProductNos(Set<String> productNos);

    List<VersionDiffDetail> queryDeployDiff(List<SceneStruct.ProductLimit> productLimitList, List<SceneStruct.ProductLimit> lastProductLimitList) throws Exception;

}
