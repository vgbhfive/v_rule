package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.enums.ProductType;
import com.vgbhfive.v_rule.common.exception.DataBaseException;
import com.vgbhfive.v_rule.common.utils.NoGenerateUtil;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.product.ProductInterestListDto;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductEntity;
import com.vgbhfive.v_rule.entity.ProductInterestEntity;
import com.vgbhfive.v_rule.mapper.ProductInterestMapper;
import com.vgbhfive.v_rule.mapper.ProductMapper;
import com.vgbhfive.v_rule.service.ProductInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @projectName: v_rule
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
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResponseContent create(ProductInterestEntity productInterestEntity, boolean isUpdate) {
        Date now = new Date();
        if (isUpdate) {
            productInterestEntity.setVersion(productInterestEntity.getVersion() + 1);
        } else {
            productInterestEntity.setProductNo(noGenerateUtil.generateNo(Constant.NO_LL));
            productInterestEntity.setCreateAt(now);
        }
        productInterestEntity.setId(null);
        productInterestEntity.setUpdateAt(now);
        Integer insertInterest = productInterestMapper.insert(productInterestEntity);
        if (insertInterest < 1) {
            throw new DataBaseException("创建利率产品失败");
        }
        ProductEntity productEntity = buildProductEntity(productInterestEntity);
        Integer insert = productMapper.insert(productEntity);
        if (insert < 1) {
            throw new DataBaseException("创建利率产品失败");
        }
        return ResponseContent.success();
    }

    private ProductEntity buildProductEntity(ProductInterestEntity productInterestEntity) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(null);
        productEntity.setLineNo(productInterestEntity.getLineNo());
        productEntity.setProductName(productInterestEntity.getProductName());
        productEntity.setProductNo(productInterestEntity.getProductNo());
        productEntity.setType(ProductType.INTEREST.getType());
        productEntity.setRemark(productInterestEntity.getRemark());
        productEntity.setVersion(productInterestEntity.getVersion());
        productEntity.setIsValid(1);
        productEntity.setIsDelete(0);
        productEntity.setCreateAt(productInterestEntity.getCreateAt());
        productEntity.setUpdateAt(new Date());
        return productEntity;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
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

    @Override
    public List<SceneStruct.ProductInterest> queryInterestByProductNos(Set<String> productInterestNoSet) {
        return productInterestMapper.queryInterestByProductNos(productInterestNoSet);
    }

}
