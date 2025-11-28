package com.vgbhfive.service.impl;

import com.vgbhfive.common.utils.NoGenerateUtil;
import com.vgbhfive.dto.PageResponse;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.product.ProductInterestListDto;
import com.vgbhfive.dto.product.ProductPeriodListDto;
import com.vgbhfive.dto.product.ProductQueryParam;
import com.vgbhfive.entity.ProductPeriodEntity;
import com.vgbhfive.mapper.ProductPeriodMapper;
import com.vgbhfive.service.ProductPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
