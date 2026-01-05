package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.exception.DataBaseException;
import com.vgbhfive.v_rule.common.utils.CompareUtil;
import com.vgbhfive.v_rule.common.utils.NoGenerateUtil;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.deploy.DetailCompareResult;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.deploy.VersionDiffDetail;
import com.vgbhfive.v_rule.dto.product.ProductCustomListDto;
import com.vgbhfive.v_rule.dto.product.ProductQueryParam;
import com.vgbhfive.v_rule.entity.ProductCustomEntity;
import com.vgbhfive.v_rule.entity.ProductEntity;
import com.vgbhfive.v_rule.mapper.ProductCustomMapper;
import com.vgbhfive.v_rule.mapper.ProductMapper;
import com.vgbhfive.v_rule.service.ProductCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author vgbhfive
 * @Date 2025/12/2 16:59
 */
@Service
public class ProductCustomServiceImpl implements ProductCustomService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductCustomMapper productCustomMapper;
    @Resource
    private NoGenerateUtil noGenerateUtil;

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
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResponseContent create(ProductEntity productEntity, boolean isUpdate) {
        Date now = new Date();
        if (isUpdate) {
            productEntity.setVersion(productEntity.getVersion() + 1);
        } else {
            productEntity.setProductNo(noGenerateUtil.generateNo(Constant.NO_ZDY));
            productEntity.setVersion(1);
            productEntity.setCreateAt(now);
        }
        productEntity.setId(null);
        productEntity.setIsValid(1);
        productEntity.setIsDelete(0);
        productEntity.setUpdateAt(now);

        List<ProductCustomEntity> customEntityList = buildCustomEntityList(productEntity);
        Integer insertCustom = productCustomMapper.batchInsert(customEntityList);
        if (insertCustom < productEntity.getProductCustomEntityList().size()) {
            throw new DataBaseException("创建自定义产品失败");
        }
        Integer insert = productMapper.insert(productEntity);
        if (insert < 1) {
            throw new DataBaseException("创建自定义产品失败");
        }
        return ResponseContent.success();
    }

    private List<ProductCustomEntity> buildCustomEntityList(ProductEntity entity) {
        Date now = new Date();
        entity.getProductCustomEntityList().forEach(customEntity -> {
            customEntity.setProductNo(entity.getProductNo());
            customEntity.setVersion(entity.getVersion());
            customEntity.setIsDelete(0);
            customEntity.setCreateAt(entity.getCreateAt());
            customEntity.setUpdateAt(now);
        });
        return entity.getProductCustomEntityList();
    }

    @Override
    public ResponseContent detail(Integer id) {
        return ResponseContent.success(productCustomMapper.queryDetailById(id));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResponseContent update(ProductEntity productEntity) {
        ProductCustomEntity oldProductCustomEntity = new ProductCustomEntity();
        oldProductCustomEntity.setProductNo(productEntity.getProductNo());
        oldProductCustomEntity.setIsDelete(1);
        productCustomMapper.update(oldProductCustomEntity,
                new UpdateWrapper<ProductCustomEntity>().eq("product_no", productEntity.getProductNo()).eq("is_delete", 0));
        ProductEntity oldProductEntity = new ProductEntity();
        oldProductEntity.setProductNo(productEntity.getProductNo());
        oldProductCustomEntity.setIsDelete(1);
        Integer update = productMapper.update(oldProductEntity,
                new UpdateWrapper<ProductEntity>().eq("id", productEntity.getId()).eq("is_delete", 0));
        if (update < 1) {
            throw new DataBaseException("修改自定义产品失败");
        }
        return this.create(productEntity, true);
    }

    @Override
    public List<SceneStruct.ProductCustom> queryCustomByProductNos(Set<String> productNos) {
        if (productNos.isEmpty()) {
            return new ArrayList<>();
        }
        List<SceneStruct.ProductCustom> customList = productCustomMapper.queryCustomByProductNos(productNos);
        customList.forEach(custom -> {
            custom.setProductCustomDetailList(productCustomMapper.queryCustomDetailByProductNo(custom.getProductNo()));
        });
        return customList;
    }

    @Override
    public List<VersionDiffDetail> queryDeployDiff(List<SceneStruct.ProductCustom> productCustomList, List<SceneStruct.ProductCustom> lastProductCustomList) throws Exception {
        List<VersionDiffDetail> versionDiffDetailList = new ArrayList<>();
        Map<String, SceneStruct.ProductCustom> lastProductCustomMap = new HashMap<>();
        lastProductCustomList.forEach(lastProductCustom -> lastProductCustomMap.put(lastProductCustom.getProductNo(), lastProductCustom));

        List<String> ignoreList = new ArrayList<>();
        ignoreList.add("version");
        for (SceneStruct.ProductCustom productCustom : productCustomList) {
            List<DetailCompareResult> detailCompareResultList;
            if (lastProductCustomMap.containsKey(productCustom.getProductNo())) {
                SceneStruct.ProductCustom lastProductCustom = lastProductCustomMap.get(productCustom.getProductNo());
                detailCompareResultList = CompareUtil.compare(lastProductCustom, productCustom, ignoreList);
                lastProductCustomList.remove(lastProductCustom);
            } else {
                detailCompareResultList = CompareUtil.compare(null, productCustom, null);
            }
            if (!detailCompareResultList.isEmpty()) {
                versionDiffDetailList.add(new VersionDiffDetail(productCustom.getProductNo(), productCustom.getProductName(), detailCompareResultList));
            }
        }

        for (SceneStruct.ProductCustom lastProductCustom : lastProductCustomList) {
            List<DetailCompareResult> detailCompareResultList = CompareUtil.compare(lastProductCustom, null, null);
            versionDiffDetailList.add(new VersionDiffDetail(lastProductCustom.getProductNo(), lastProductCustom.getProductName(), detailCompareResultList));
        }
        return versionDiffDetailList;
    }

}
