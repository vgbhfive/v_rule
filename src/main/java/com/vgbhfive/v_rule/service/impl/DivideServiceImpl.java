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
import com.vgbhfive.v_rule.dto.divide.DivideListDto;
import com.vgbhfive.v_rule.dto.divide.DivideQueryParam;
import com.vgbhfive.v_rule.entity.DivideEntity;
import com.vgbhfive.v_rule.entity.DivideProductEntity;
import com.vgbhfive.v_rule.mapper.DivideMapper;
import com.vgbhfive.v_rule.mapper.DivideProductMapper;
import com.vgbhfive.v_rule.service.DivideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 15:51
 */
@Service
public class DivideServiceImpl implements DivideService {

    @Autowired
    private DivideMapper divideMapper;
    @Autowired
    private DivideProductMapper divideProductMapper;
    @Resource
    private NoGenerateUtil noGenerateUtil;

    @Override
    public ResponseContent queryList(DivideQueryParam param) {
        int start = (param.getCurrPage() - 1) * param.getLimit();
        int limit = param.getLimit();

        List<DivideListDto> divideListDtoList = divideMapper.queryList(param, start, limit);
        int totalCount = divideMapper.queryTotalCount(param);

        int totalPage = (totalCount - 1) / limit + 1;
        PageResponse<DivideListDto> result = new PageResponse<>(param.getCurrPage(), limit, totalCount, totalPage, divideListDtoList);
        return ResponseContent.success(result);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResponseContent create(DivideEntity divideEntity, boolean isUpdate) {
        Date now = new Date();
        if (isUpdate) {
            divideEntity.setVersion(divideEntity.getVersion() + 1);
        } else {
            divideEntity.setDivideNo(noGenerateUtil.generateNo(Constant.NO_FLQ));
            divideEntity.setVersion(1);
            divideEntity.setCreateAt(now);
        }
        List<DivideProductEntity> productEntityList = buildProductEntityList(divideEntity);
        Integer insertProduct = divideProductMapper.batchInsert(productEntityList);
        if (insertProduct < divideEntity.getProductEntityList().size()) {
            throw new DataBaseException("创建分流器失败");
        }
        divideEntity.setId(null);
        divideEntity.setIsValid(1);
        divideEntity.setIsDelete(0);
        divideEntity.setUpdateAt(now);
        Integer insert = divideMapper.insert(divideEntity);
        if (insert < 1) {
            throw new DataBaseException("创建分流器失败");
        }
        return ResponseContent.success();
    }

    @Override
    public ResponseContent detail(Integer id) {
        return ResponseContent.success(divideMapper.queryDetailById(id));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResponseContent update(DivideEntity divideEntity) {
        DivideEntity oldDivideEntity = new DivideEntity();
        oldDivideEntity.setIsDelete(1);
        oldDivideEntity.setUpdateAt(new Date());
        Integer update = divideMapper.update(oldDivideEntity,
                new UpdateWrapper<DivideEntity>().eq("id", divideEntity.getId()).eq("is_delete", 0));
        if (update < 1) {
            throw new DataBaseException("修改分流器失败");
        }
        DivideProductEntity oldDivideProductEntity = new DivideProductEntity();
        oldDivideProductEntity.setIsDelete(1);
        oldDivideProductEntity.setUpdateAt(new Date());
        divideProductMapper.update(oldDivideProductEntity,
                new UpdateWrapper<DivideProductEntity>().eq("divide_no", divideEntity.getDivideNo()).eq("is_delete", 0));
        return this.create(divideEntity, true);
    }

    private List<DivideProductEntity> buildProductEntityList(DivideEntity divideEntity) {
        List<DivideProductEntity> productEntityList = new ArrayList<>();
        Date now = new Date();
        divideEntity.getProductEntityList().forEach(productEntity -> {
            productEntity.setId(null);
            productEntity.setDivideNo(divideEntity.getDivideNo());
            productEntity.setVersion(divideEntity.getVersion());
            productEntity.setIsDelete(0);
            productEntity.setCreateAt(now);
            productEntity.setUpdateAt(now);
            productEntityList.add(productEntity);
        });
        return productEntityList;
    }

    @Override
    public List<SceneStruct.Divide> queryDivideBySceneNo(String sceneNo) {
        List<SceneStruct.Divide> divideList = divideMapper.queryDivideBySceneNo(sceneNo);
        divideList.forEach(divide -> {
            List<String> interestNoList = divideProductMapper.queryProductNoListByDivideNo(divide.getNo(), "interest");
            List<String> periodNoList = divideProductMapper.queryProductNoListByDivideNo(divide.getNo(), "period");
            List<String> limitNoList = divideProductMapper.queryProductNoListByDivideNo(divide.getNo(), "limit");
            List<String> customNoList = divideProductMapper.queryProductNoListByDivideNo(divide.getNo(), "custom");
            divide.setProductInterestNoList(interestNoList);
            divide.setProductPeriodNoList(periodNoList);
            divide.setProductLimitNoList(limitNoList);
            divide.setProductCustomNoList(customNoList);
            divide.setProductNoList(new ArrayList<String>(){{
                addAll(interestNoList);
                addAll(periodNoList);
                addAll(limitNoList);
                addAll(customNoList);
            }});
        });
        return divideList;
    }

    @Override
    public List<VersionDiffDetail> queryDeployDiff(List<SceneStruct.Divide> divideList, List<SceneStruct.Divide> lastDivideList) throws Exception {
        List<VersionDiffDetail> versionDiffDetailList = new ArrayList<>();
        // 上次发布分流器
        Map<String, SceneStruct.Divide> lastDivideMap = new HashMap<>();
        lastDivideList.forEach(divide -> lastDivideMap.put(divide.getNo(), divide));

        // 与最近一次上线版本分流器对比
        List<String> ignoreList = new ArrayList<>();
        ignoreList.add("version");
        ignoreList.add("isValid");
        for (SceneStruct.Divide divide : divideList) {
            List<DetailCompareResult> detailCompareResultList;
            if (lastDivideMap.containsKey(divide.getNo())) {
                SceneStruct.Divide lastDivide = lastDivideMap.get(divide.getNo());
                detailCompareResultList = CompareUtil.compare(lastDivide, divide, ignoreList);
                lastDivideList.remove(lastDivide);
            } else {
                // 现在多配置的分流器
                detailCompareResultList = CompareUtil.compare(null, divide, null);
            }
            if (!detailCompareResultList.isEmpty()) {
                versionDiffDetailList.add(new VersionDiffDetail(divide.getNo(), divide.getName(), detailCompareResultList));
            }
        }

        // 相比现在多配置的分流器
        for (SceneStruct.Divide lastDeployDivide : lastDivideList) {
            List<DetailCompareResult> detailCompareResultList = CompareUtil.compare(lastDeployDivide, null, null);
            versionDiffDetailList.add(new VersionDiffDetail(lastDeployDivide.getNo(), lastDeployDivide.getName(), detailCompareResultList));
        }
        return versionDiffDetailList;
    }

    @Override
    public ResponseContent updateDivideDeployTime(List<SceneStruct.Divide> divideList, Date deployTime) {
        Integer update = divideMapper.updateDeployTimeBatch(divideList, deployTime);
        if (update != divideList.size()) {
            throw new DataBaseException("更新分流器上线时间失败");
        }
        return ResponseContent.success();
    }

}
