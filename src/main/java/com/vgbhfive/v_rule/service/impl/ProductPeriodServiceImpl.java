package com.vgbhfive.v_rule.service.impl;

import com.vgbhfive.v_rule.common.utils.NoGenerateUtil;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.product.ProductPeriodListDto;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductPeriodEntity;
import com.vgbhfive.v_rule.mapper.ProductPeriodMapper;
import com.vgbhfive.v_rule.service.ProductPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 11:31
 */
@Service
public class ProductPeriodServiceImpl implements ProductPeriodService {

    @Autowired
    private ProductPeriodMapper productPeriodMapper;
    @Resource
    private NoGenerateUtil noGenerateUtil;

    @Override
    public ResponseContent queryList(ProductQueryParam param) {
        int start = (param.getCurrPage() - 1) * param.getLimit();
        int limit = param.getLimit();

        List<ProductPeriodListDto> productPeriodListDtoList = productPeriodMapper.queryList(param, start, limit);
        int totalCount = productPeriodMapper.queryTotalCount(param);

        int totalPage = (totalCount - 1) / limit + 1;
        PageResponse<ProductPeriodListDto> result = new PageResponse<>(param.getCurrPage(), limit, totalCount, totalPage, productPeriodListDtoList);
        return ResponseContent.success(result);
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
    public List<SceneStruct.ProductPeriod> queryPeriodByProductNos(Set<String> productNos) {
        return productPeriodMapper.queryPeriodByProductNos(productNos);
    }
}
