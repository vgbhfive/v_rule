package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.deploy.VersionDiffDetail;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductInterestEntity;

import java.util.List;
import java.util.Set;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/27 23:04
 */
public interface ProductInterestService {

    ResponseContent queryList(ProductQueryParam param);

    ResponseContent create(ProductInterestEntity productInterestEntity, boolean isUpdate);

    ResponseContent update(ProductInterestEntity productInterestEntity);

    List<SceneStruct.ProductInterest> queryInterestByProductNos(Set<String> productInterestNoSet);

    List<VersionDiffDetail> queryDeployDiff(List<SceneStruct.ProductInterest> productInterestList, List<SceneStruct.ProductInterest> lastProductInterestList) throws Exception;

}
