package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.exception.DataBaseException;
import com.vgbhfive.v_rule.common.utils.CompareUtil;
import com.vgbhfive.v_rule.common.utils.NoGenerateUtil;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.datacategory.DataCategoryListDto;
import com.vgbhfive.v_rule.dto.datacategory.DataCategoryQueryParam;
import com.vgbhfive.v_rule.dto.deploy.DetailCompareResult;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.deploy.VersionDiffDetail;
import com.vgbhfive.v_rule.entity.DataCategoryEntity;
import com.vgbhfive.v_rule.mapper.DataCategoryMapper;
import com.vgbhfive.v_rule.service.DataCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/26 21:35
 */
@Service
public class DataCategoryServiceImpl implements DataCategoryService {

    @Resource
    private DataCategoryMapper dataCategoryMapper;
    @Resource
    private NoGenerateUtil noGenerateUtil;

    @Override
    public ResponseContent queryList(DataCategoryQueryParam param) {
        int start = (param.getCurrPage() - 1) * param.getLimit();
        int limit = param.getLimit();

        List<DataCategoryListDto> dataCategoryListDtoList = dataCategoryMapper.queryList(param, start, limit);
        int totalCount = dataCategoryMapper.queryTotalCount(param);

        int totalPage = (totalCount - 1) / limit + 1;
        PageResponse<DataCategoryListDto> result = new PageResponse<>(param.getCurrPage(), limit, totalCount, totalPage, dataCategoryListDtoList);
        return ResponseContent.success(result);
    }

    @Override
    public ResponseContent create(DataCategoryEntity dataCategoryEntity, boolean isUpdate) {
        Date now = new Date();
        if (isUpdate) {
            dataCategoryEntity.setVersion(dataCategoryEntity.getVersion() + 1);
        } else {
            dataCategoryEntity.setDataCategoryNo(noGenerateUtil.generateNo(Constant.NO_SJYFL));
            dataCategoryEntity.setCreateAt(now);
        }
        dataCategoryEntity.setId(null);
        dataCategoryEntity.setIsValid(1);
        dataCategoryEntity.setIsDelete(0);
        dataCategoryEntity.setUpdateAt(now);
        Integer insert = dataCategoryMapper.insert(dataCategoryEntity);
        if (insert < 1) {
            throw new DataBaseException("创建数据源分类失败");
        }
        return ResponseContent.success();
    }

    @Override
    public ResponseContent update(DataCategoryEntity dataCategoryEntity) {
        DataCategoryEntity oldDataCategoryEntity = new DataCategoryEntity();
        oldDataCategoryEntity.setIsDelete(1);
        oldDataCategoryEntity.setUpdateAt(new Date());
        Integer update = dataCategoryMapper.update(oldDataCategoryEntity,
                new UpdateWrapper<DataCategoryEntity>().eq("id", dataCategoryEntity.getId()).eq("is_delete", 0));
        if (update < 1) {
            throw new DataBaseException("更新数据源分类失败");
        }
        return this.create(dataCategoryEntity, true);
    }

    @Override
    public List<SceneStruct.DataCategory> queryDataCategoryByDataCategoryNos(Set<String> dataCategoryNoSet) {
        if (dataCategoryNoSet.isEmpty()) {
            return new ArrayList<>();
        }
        return dataCategoryMapper.queryDataCategoryByDataCategoryNos(dataCategoryNoSet);
    }

    @Override
    public List<VersionDiffDetail> queryDeployDiff(List<SceneStruct.DataCategory> dataCategoryList, List<SceneStruct.DataCategory> lastDataCategoryList) throws Exception {
        List<VersionDiffDetail> versionDiffDetailList = new ArrayList<>();
        Map<String, SceneStruct.DataCategory> lastDataCategoryMap = new HashMap<>();
        lastDataCategoryList.forEach(lastDataCategory -> lastDataCategoryMap.put(lastDataCategory.getDataCategoryNo(), lastDataCategory));

        List<String> ignoreList = new ArrayList<>();
        ignoreList.add("version");
        ignoreList.add("isValid");
        for (SceneStruct.DataCategory dataCategory : dataCategoryList) {
            List<DetailCompareResult> detailCompareResultList;
            if (lastDataCategoryMap.containsKey(dataCategory.getDataCategoryNo())) {
                SceneStruct.DataCategory lastDataCategory = lastDataCategoryMap.get(dataCategory.getDataCategoryNo());
                detailCompareResultList = CompareUtil.compare(lastDataCategory, dataCategory, ignoreList);
                lastDataCategoryList.remove(lastDataCategory);
            } else {
                detailCompareResultList = CompareUtil.compare(null, dataCategory, null);
            }
            if (!detailCompareResultList.isEmpty()) {
                versionDiffDetailList.add(new VersionDiffDetail(dataCategory.getDataCategoryNo(), dataCategory.getDataCategoryName(), detailCompareResultList));
            }
        }

        for (SceneStruct.DataCategory lastDataCategory : lastDataCategoryList) {
            List<DetailCompareResult> detailCompareResultList = CompareUtil.compare(lastDataCategory, null, null);
            versionDiffDetailList.add(new VersionDiffDetail(lastDataCategory.getDataCategoryNo(), lastDataCategory.getDataCategoryName(), detailCompareResultList));
        }
        return versionDiffDetailList;
    }

}
