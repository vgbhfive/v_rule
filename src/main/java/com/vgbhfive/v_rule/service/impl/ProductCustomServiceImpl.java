package com.vgbhfive.v_rule.service.impl;

import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.product.ProductCustomListDto;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductLimitEntity;
import com.vgbhfive.v_rule.mapper.ProductCustomMapper;
import com.vgbhfive.v_rule.service.ProductCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author vgbhfive
 * @Date 2025/12/2 16:59
 */
@Service
public class ProductCustomServiceImpl implements ProductCustomService {

    @Autowired
    private ProductCustomMapper productCustomMapper;

    @Override
    public ResponseContent queryList(ProductQueryParam param) {
        int start = (param.getCurrPage() - 1) * param.getLimit();
        int limit = param.getLimit();

        List<ProductCustomListDto> productCustomListDtoList = productCustomMapper.queryList(param, start, limit);
        int totalCount = productCustomMapper.queryTotalCount(param);

        int totalPage = (totalCount - 1) / limit + 1;
        PageResponse<ProductCustomListDto> result = new PageResponse<>(param.getCurrPage(), limit, totalCount, totalPage, productCustomListDtoList);
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

    @Override
    public List<SceneStruct.ProductCustom> queryCustomByProductNos(Set<String> productNos) {
        List<SceneStruct.ProductCustom> customList = productCustomMapper.queryCustomByProductNos(productNos);
        customList.forEach(custom -> {
            custom.setProductCustomDetailList(productCustomMapper.queryCustomDetailByProductNo(custom.getProductNo()));
        });
        return customList;
    }

}
