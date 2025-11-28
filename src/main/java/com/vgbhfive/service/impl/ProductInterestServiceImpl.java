package com.vgbhfive.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.common.constants.Constant;
import com.vgbhfive.common.exception.DataBaseException;
import com.vgbhfive.common.utils.NoGenerateUtil;
import com.vgbhfive.dto.PageResponse;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.product.ProductInterestListDto;
import com.vgbhfive.dto.product.ProductQueryParam;
import com.vgbhfive.entity.ProductEntity;
import com.vgbhfive.entity.ProductInterestEntity;
import com.vgbhfive.mapper.ProductInterestMapper;
import com.vgbhfive.mapper.ProductMapper;
import com.vgbhfive.service.ProductInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/27 23:06
 */
@Service
public class ProductInterestServiceImpl implements ProductInterestService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductInterestMapper productInterestMapper;
    @Resource
    private NoGenerateUtil noGenerateUtil;

    @Override
    public ResponseContent queryList(ProductQueryParam param) {
        int start = (param.getCurrPage() - 1) * param.getLimit();
        int limit = param.getLimit();

        List<ProductInterestListDto> productInterestListDtoList = productInterestMapper.queryList(param, start, limit);
        int totalCount = productInterestMapper.queryTotalCount(param);

        int totalPage = (totalCount - 1) / limit + 1;
        PageResponse<ProductInterestListDto> result = new PageResponse<>(param.getCurrPage(), limit, totalCount, totalPage, productInterestListDtoList);
        return ResponseContent.success(result);
    }

    @Override
    @Transactional
    public ResponseContent create(ProductInterestEntity productInterestEntity, boolean isUpdate) {
        if (isUpdate) {
            productInterestEntity.setVersion(productInterestEntity.getVersion() + 1);
        } else {
            productInterestEntity.setProductNo(noGenerateUtil.generateNo(Constant.NO_LL));
        }
        ProductEntity productEntity = buildProductEntity(productInterestEntity);
        Integer insert = productMapper.insert(productEntity);

        productInterestEntity.setId(null);
        Date now = new Date();
        productInterestEntity.setCreateAt(now);
        productInterestEntity.setUpdateAt(now);
        Integer insertInterest = productInterestMapper.insert(productInterestEntity);
        if (insert < 1 || insertInterest < 1) {
            throw new DataBaseException("创建利率产品失败");
        }
        return ResponseContent.success();
    }

    @Override
    @Transactional
    public ResponseContent update(ProductInterestEntity productInterestEntity) {
        ProductEntity oldProductEntity = new ProductEntity();
        oldProductEntity.setIsDelete(1);
        oldProductEntity.setUpdateAt(new Date());
        Integer update = productMapper.update(oldProductEntity,
                new UpdateWrapper<ProductEntity>().eq("id", productInterestEntity.getId()).eq("is_delete", 0));
        ProductInterestEntity oldProductInterestEntity = new ProductInterestEntity();
        oldProductInterestEntity.setIsDelete(1);
        oldProductInterestEntity.setUpdateAt(new Date());
        Integer updateInterest = productInterestMapper.update(oldProductInterestEntity,
                new UpdateWrapper<ProductInterestEntity>().eq("product_no", productInterestEntity.getProductNo()).eq("is_delete", 0));
        if (update < 1 || updateInterest < 1) {
            throw new DataBaseException("更新利率产品失败");
        }
        return this.create(productInterestEntity, true);
    }

    private ProductEntity buildProductEntity(ProductInterestEntity productInterestEntity) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(null);
        productEntity.setLineNo(productInterestEntity.getLineNo());
        productEntity.setProductName(productInterestEntity.getProductName());
        productEntity.setProductNo(productInterestEntity.getProductNo());
        productEntity.setType(productInterestEntity.getType());
        productEntity.setRemark(productInterestEntity.getRemark());
        productEntity.setVersion(productInterestEntity.getVersion());
        productEntity.setIsValid(productInterestEntity.getIsValid());
        productEntity.setIsDelete(productInterestEntity.getIsDelete());
        Date now = new Date();
        productEntity.setCreateAt(now);
        productEntity.setUpdateAt(now);
        return productEntity;
    }


}
