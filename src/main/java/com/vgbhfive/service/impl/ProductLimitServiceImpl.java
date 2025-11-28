package com.vgbhfive.service.impl;

import com.vgbhfive.common.utils.NoGenerateUtil;
import com.vgbhfive.dto.PageResponse;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.product.ProductInterestListDto;
import com.vgbhfive.dto.product.ProductLimitListDto;
import com.vgbhfive.dto.product.ProductPeriodListDto;
import com.vgbhfive.dto.product.ProductQueryParam;
import com.vgbhfive.entity.ProductLimitEntity;
import com.vgbhfive.mapper.ProductLimitMapper;
import com.vgbhfive.service.ProductLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 11:43
 */
@Service
public class ProductLimitServiceImpl implements ProductLimitService {

    @Autowired
    private ProductLimitMapper productLimitMapper;
    @Resource
    private NoGenerateUtil noGenerateUtil;

    @Override
    public ResponseContent queryList(ProductQueryParam param) {
        int start = (param.getCurrPage() - 1) * param.getLimit();
        int limit = param.getLimit();

        List<ProductLimitListDto> productLimitListDtoList = productLimitMapper.queryList(param, start, limit);
        int totalCount = productLimitMapper.queryTotalCount(param);

        int totalPage = (totalCount - 1) / limit + 1;
        PageResponse<ProductLimitListDto> result = new PageResponse<>(param.getCurrPage(), limit, totalCount, totalPage, productLimitListDtoList);
        return ResponseContent.success(result);
    }

    @Override
    public ResponseContent create(ProductLimitEntity productLimitEntity, boolean isUpdate) {
        return null;
    }

    @Override
    public ResponseContent update(ProductLimitEntity productLimitEntity) {
        return null;
    }
}
