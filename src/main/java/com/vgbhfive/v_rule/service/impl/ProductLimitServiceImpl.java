package com.vgbhfive.v_rule.service.impl;

import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.utils.NoGenerateUtil;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.product.ProductLimitListDto;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductLimitEntity;
import com.vgbhfive.v_rule.mapper.ProductLimitMapper;
import com.vgbhfive.v_rule.service.ProductLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
        Date now = new Date();
        if (isUpdate) {
            productLimitEntity.setVersion(productLimitEntity.getVersion() + 1);
        } else {
            productLimitEntity.setProductNo(noGenerateUtil.generateNo(Constant.NO_ED));
            productLimitEntity.setCreateAt(now);
        }
        productLimitEntity.setId(null);
        productLimitEntity.setUpdateAt(now);
        return null;
    }

    @Override
    public ResponseContent update(ProductLimitEntity productLimitEntity) {
        return null;
    }

    @Override
    public List<SceneStruct.ProductLimit> queryLimitByProductNos(Set<String> productNos) {
        return productLimitMapper.queryLimitByProductNos(productNos);
    }

}
