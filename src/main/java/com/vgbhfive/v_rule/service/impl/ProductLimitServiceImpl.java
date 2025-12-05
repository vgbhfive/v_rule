package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.enums.ProductType;
import com.vgbhfive.v_rule.common.exception.DataBaseException;
import com.vgbhfive.v_rule.common.utils.CompareUtil;
import com.vgbhfive.v_rule.common.utils.NoGenerateUtil;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.DetailCompareResult;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.deploy.VersionDiffDetail;
import com.vgbhfive.v_rule.dto.product.ProductLimitListDto;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductEntity;
import com.vgbhfive.v_rule.entity.ProductLimitEntity;
import com.vgbhfive.v_rule.mapper.ProductLimitMapper;
import com.vgbhfive.v_rule.mapper.ProductMapper;
import com.vgbhfive.v_rule.service.ProductLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 11:43
 */
@Service
public class ProductLimitServiceImpl implements ProductLimitService {

    @Autowired
    private ProductLimitMapper productLimitMapper;
    @Autowired
    private ProductMapper productMapper;
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
        productLimitEntity.setIsValid(1);
        productLimitEntity.setIsDelete(0);
        productLimitEntity.setUpdateAt(now);
        Integer insertLimit = productLimitMapper.insert(productLimitEntity);
        if (insertLimit < 1) {
            throw new DataBaseException("新增额度产品失败");
        }
        ProductEntity productEntity = buildProductEntity(productLimitEntity);
        Integer insert = productMapper.insert(productEntity);
        if (insert < 1) {
            throw new DataBaseException("新增额度产品失败");
        }
        return ResponseContent.success();
    }

    private ProductEntity buildProductEntity(ProductLimitEntity productLimitEntity) {
        ProductEntity entity = new ProductEntity();
        entity.setId(null);
        entity.setLineNo(productLimitEntity.getLineNo());
        entity.setProductName(productLimitEntity.getProductName());
        entity.setProductNo(productLimitEntity.getProductNo());
        entity.setType(ProductType.LIMIT.getType());
        entity.setRemark(productLimitEntity.getRemark());
        entity.setVersion(productLimitEntity.getVersion());
        entity.setIsValid(1);
        entity.setIsDelete(0);
        entity.setCreateAt(productLimitEntity.getCreateAt());
        entity.setUpdateAt(new Date());
        return entity;
    }

    @Override
    public ResponseContent update(ProductLimitEntity productLimitEntity) {
        ProductEntity oldProductEntity = new ProductEntity();
        oldProductEntity.setId(productLimitEntity.getId());
        oldProductEntity.setIsDelete(1);
        Integer update = productMapper.update(oldProductEntity,
                new UpdateWrapper<ProductEntity>().eq("id", productLimitEntity.getId()).eq("is_delete", 0));
        if (update < 1) {
            throw new DataBaseException("修改额度产品失败");
        }
        ProductLimitEntity oldProductLimitEntity = new ProductLimitEntity();
        oldProductLimitEntity.setProductNo(productLimitEntity.getProductNo());
        oldProductLimitEntity.setIsDelete(1);
        Integer updateLimit = productLimitMapper.update(oldProductLimitEntity,
                new UpdateWrapper<ProductLimitEntity>().eq("product_no", productLimitEntity.getProductNo()).eq("is_delete", 0));
        if (updateLimit < 1) {
            throw new DataBaseException("修改额度产品失败");
        }
        return this.create(productLimitEntity, true);
    }

    @Override
    public List<SceneStruct.ProductLimit> queryLimitByProductNos(Set<String> productNos) {
        if (productNos.isEmpty()) {
            return new ArrayList<>();
        }
        return productLimitMapper.queryLimitByProductNos(productNos);
    }

    @Override
    public List<VersionDiffDetail> queryDeployDiff(List<SceneStruct.ProductLimit> productLimitList, List<SceneStruct.ProductLimit> lastProductLimitList) throws Exception {
        List<VersionDiffDetail> versionDiffDetailList = new ArrayList<>();
        Map<String, SceneStruct.ProductLimit> lastProductLimitMap = new HashMap<>();
        lastProductLimitList.forEach(lastProductLimit -> lastProductLimitMap.put(lastProductLimit.getProductNo(), lastProductLimit));

        List<String> ignoreList = new ArrayList<>();
        ignoreList.add("version");
        for (SceneStruct.ProductLimit productLimit : productLimitList) {
            List<DetailCompareResult> detailCompareResultList;
            if (lastProductLimitMap.containsKey(productLimit.getProductNo())) {
                SceneStruct.ProductLimit lastProductLimit = lastProductLimitMap.get(productLimit.getProductNo());
                detailCompareResultList = CompareUtil.compare(lastProductLimit, productLimit, ignoreList);
                lastProductLimitList.remove(lastProductLimit);
            } else {
                detailCompareResultList = CompareUtil.compare(null, productLimit, null);
            }
            if (!detailCompareResultList.isEmpty()) {
                versionDiffDetailList.add(new VersionDiffDetail(productLimit.getProductNo(), productLimit.getProductName(), detailCompareResultList));
            }
        }

        for (SceneStruct.ProductLimit lastProductLimit : lastProductLimitList) {
            List<DetailCompareResult> detailCompareResultList = CompareUtil.compare(lastProductLimit, null, null);
            versionDiffDetailList.add(new VersionDiffDetail(lastProductLimit.getProductNo(), lastProductLimit.getProductName(), detailCompareResultList));
        }
        return versionDiffDetailList;
    }

}
