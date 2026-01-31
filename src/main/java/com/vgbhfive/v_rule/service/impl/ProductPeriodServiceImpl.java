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
import com.vgbhfive.v_rule.dto.product.ProductPeriodListDto;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductEntity;
import com.vgbhfive.v_rule.entity.ProductPeriodEntity;
import com.vgbhfive.v_rule.mapper.ProductMapper;
import com.vgbhfive.v_rule.mapper.ProductPeriodMapper;
import com.vgbhfive.v_rule.service.ProductPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 11:31
 */
@Service
public class ProductPeriodServiceImpl implements ProductPeriodService {

    @Autowired
    private ProductPeriodMapper productPeriodMapper;
    @Autowired
    private ProductMapper productMapper;
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
        Date now = new Date();
        if (isUpdate) {
            productPeriodEntity.setVersion(productPeriodEntity.getVersion() + 1);
        } else {
            productPeriodEntity.setProductNo(noGenerateUtil.generateNo(Constant.NO_ZQ));
            productPeriodEntity.setVersion(1);
            productPeriodEntity.setCreateAt(now);
        }
        productPeriodEntity.setId(null);
        productPeriodEntity.setIsDelete(0);
        productPeriodEntity.setUpdateAt(now);
        int insertPeriod = productPeriodMapper.insert(productPeriodEntity);
        if (insertPeriod < 1) {
            throw new DataBaseException("新增账期产品失败");
        }
        ProductEntity productEntity = buildProductEntity(productPeriodEntity);
        int insert = productMapper.insert(productEntity);
        if (insert < 1) {
            throw new DataBaseException("新增账期产品失败");
        }
        return ResponseContent.success(String.format("%s账期产品成功", isUpdate ? "修改" : "新增"));
    }

    private ProductEntity buildProductEntity(ProductPeriodEntity productPeriodEntity) {
        ProductEntity entity = new ProductEntity();
        entity.setId(null);
        entity.setLineNo(productPeriodEntity.getLineNo());
        entity.setProductName(productPeriodEntity.getProductName());
        entity.setProductNo(productPeriodEntity.getProductNo());
        entity.setType(ProductType.PERIOD.getType());
        entity.setRemark(productPeriodEntity.getRemark());
        entity.setVersion(productPeriodEntity.getVersion());
        entity.setIsValid(productPeriodEntity.getIsValid());
        entity.setIsDelete(0);
        entity.setCreateAt(productPeriodEntity.getCreateAt());
        entity.setUpdateAt(new Date());
        return entity;
    }

    @Override
    public ResponseContent update(ProductPeriodEntity productPeriodEntity) {
        ProductEntity oldProductEntity = new ProductEntity();
        oldProductEntity.setId(productPeriodEntity.getId());
        oldProductEntity.setIsDelete(1);
        int update = productMapper.update(oldProductEntity,
                new UpdateWrapper<ProductEntity>().eq("id", productPeriodEntity.getId()).eq("is_delete", 0));
        if (update < 1) {
            throw new DataBaseException("修改账期产品失败");
        }
        ProductPeriodEntity oldProductPeriodEntity = new ProductPeriodEntity();
        oldProductPeriodEntity.setProductName(productPeriodEntity.getProductName());
        oldProductPeriodEntity.setIsDelete(1);
        int updatePeriod = productPeriodMapper.update(oldProductPeriodEntity,
                new UpdateWrapper<ProductPeriodEntity>().eq("product_no", productPeriodEntity.getProductNo()).eq("is_delete", 0));
        if (updatePeriod < 1) {
            throw new DataBaseException("修改账期产品失败");
        }
        return this.create(productPeriodEntity, true);
    }

    @Override
    public List<SceneStruct.ProductPeriod> queryPeriodByProductNos(Set<String> productNos) {
        if (productNos.isEmpty()) {
            return new ArrayList<>();
        }
        return productPeriodMapper.queryPeriodByProductNos(productNos);
    }

    @Override
    public List<VersionDiffDetail> queryDeployDiff(List<SceneStruct.ProductPeriod> productPeriodList, List<SceneStruct.ProductPeriod> lastProductPeriodList) throws Exception {
        List<VersionDiffDetail> versionDiffDetailList = new ArrayList<>();
        Map<String, SceneStruct.ProductPeriod> lastProductPeriodMap = new HashMap<>();
        lastProductPeriodList.forEach(productPeriod -> lastProductPeriodMap.put(productPeriod.getNo(), productPeriod));

        List<String> ignoreList = new ArrayList<>();
        ignoreList.add("version");
        for (SceneStruct.ProductPeriod productPeriod : productPeriodList) {
            List<DetailCompareResult> detailCompareResultList;
            if (lastProductPeriodMap.containsKey(productPeriod.getNo())) {
                SceneStruct.ProductPeriod lastProductPeriod = lastProductPeriodMap.get(productPeriod.getNo());
                detailCompareResultList = CompareUtil.compare(lastProductPeriod, productPeriod, ignoreList);
                lastProductPeriodList.remove(lastProductPeriod);
            } else {
                detailCompareResultList = CompareUtil.compare(null, productPeriod, null);
            }
            if (!detailCompareResultList.isEmpty()) {
                versionDiffDetailList.add(new VersionDiffDetail(productPeriod.getNo(), productPeriod.getName(), detailCompareResultList));
            }
        }

        for (SceneStruct.ProductPeriod lastProductPeriod : lastProductPeriodList) {
            List<DetailCompareResult> detailCompareResultList = CompareUtil.compare(lastProductPeriod, null, null);
            versionDiffDetailList.add(new VersionDiffDetail(lastProductPeriod.getNo(), lastProductPeriod.getName(), detailCompareResultList));
        }
        return versionDiffDetailList;
    }

}
