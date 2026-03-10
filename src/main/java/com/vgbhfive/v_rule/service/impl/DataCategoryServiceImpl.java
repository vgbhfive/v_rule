package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.gson.Gson;
import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.exception.DataBaseException;
import com.vgbhfive.v_rule.common.exception.ParamException;
import com.vgbhfive.v_rule.common.utils.CompareUtil;
import com.vgbhfive.v_rule.common.utils.HttpUtil;
import com.vgbhfive.v_rule.common.utils.NoGenerateUtil;
import com.vgbhfive.v_rule.common.utils.RequestHolder;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.datacategory.DataCategoryListDto;
import com.vgbhfive.v_rule.dto.datacategory.DataCategoryQueryParam;
import com.vgbhfive.v_rule.dto.deploy.DetailCompareResult;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.deploy.VersionDiffDetail;
import com.vgbhfive.v_rule.entity.DataCategoryDetailEntity;
import com.vgbhfive.v_rule.entity.DataCategoryEntity;
import com.vgbhfive.v_rule.entity.LineEntity;
import com.vgbhfive.v_rule.mapper.DataCategoryDetailMapper;
import com.vgbhfive.v_rule.mapper.DataCategoryMapper;
import com.vgbhfive.v_rule.mapper.LineMapper;
import com.vgbhfive.v_rule.service.DataCategoryService;
import org.apache.tomcat.util.buf.UDecoder;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DataCategoryMapper dataCategoryMapper;
    @Autowired
    private DataCategoryDetailMapper dataCategoryDetailMapper;
    @Resource
    private NoGenerateUtil noGenerateUtil;
    @Autowired
    private LineMapper lineMapper;
    @Resource
    private Gson gson;
    @Resource
    private HttpUtil httpUtil;

    @Override
    public ResponseContent queryList(DataCategoryQueryParam param) {
        List<String> lineList = (List<String>) RequestHolder.get().get(Constant.LINE_PERMISSION_SET);
        param.setLineNoList(lineList);
        int start = (param.getCurrPage() - 1) * param.getLimit();
        int limit = param.getLimit();

        List<DataCategoryListDto> dataCategoryListDtoList = Objects.isNull(lineList) || !lineList.isEmpty() ?
                dataCategoryMapper.queryList(param, start, limit) : new ArrayList<>();
        int totalCount = Objects.isNull(lineList) || !lineList.isEmpty() ?
                dataCategoryMapper.queryTotalCount(param) : 0;

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
            dataCategoryEntity.setVersion(1);
            dataCategoryEntity.setCreateAt(now);
        }
        dataCategoryEntity.setId(null);
        dataCategoryEntity.setIsValid(1);
        dataCategoryEntity.setIsDelete(0);
        dataCategoryEntity.setUpdateAt(now);

        buildDetailEntityList(dataCategoryEntity.getDetailList(), dataCategoryEntity);
        int insertDetail = dataCategoryDetailMapper.batchInsertDetails(dataCategoryEntity.getDetailList());
        if (insertDetail != dataCategoryEntity.getDetailList().size()) {
            throw new DataBaseException("创建数据源分类失败");
        }
        int insert = dataCategoryMapper.insert(dataCategoryEntity);
        if (insert < 1) {
            throw new DataBaseException("创建数据源分类失败");
        }
        return ResponseContent.success(String.format("%s数据源分类成功", isUpdate ? "修改" : "新增"));
    }

    private void buildDetailEntityList(List<DataCategoryDetailEntity> detailEntityList, DataCategoryEntity dataCategoryEntity) {
        Date now = new Date();
        detailEntityList.forEach(detail -> {
            detail.setDataCategoryNo(dataCategoryEntity.getDataCategoryNo());
            detail.setVersion(dataCategoryEntity.getVersion());
            detail.setIsDelete(0);
            detail.setCreateAt(now);
            detail.setUpdateAt(now);
        });
    }

    @Override
    public ResponseContent update(DataCategoryEntity dataCategoryEntity) {
        Date now = new Date();
        DataCategoryDetailEntity oldDataCategoryDetailEntity = new DataCategoryDetailEntity();
        oldDataCategoryDetailEntity.setIsDelete(1);
        oldDataCategoryDetailEntity.setUpdateAt(now);
        int updateDetail = dataCategoryDetailMapper.update(oldDataCategoryDetailEntity, new UpdateWrapper<DataCategoryDetailEntity>().
                eq("data_category_no", dataCategoryEntity.getDataCategoryNo()).eq("is_delete", 0));
        if (updateDetail < 1) {
            throw new DataBaseException("更新数据源分类失败");
        }
        DataCategoryEntity oldDataCategoryEntity = new DataCategoryEntity();
        oldDataCategoryEntity.setIsDelete(1);
        oldDataCategoryEntity.setUpdateAt(now);
        int update = dataCategoryMapper.update(oldDataCategoryEntity,
                new UpdateWrapper<DataCategoryEntity>().eq("id", dataCategoryEntity.getId()).eq("is_delete", 0));
        if (update < 1) {
            throw new DataBaseException("更新数据源分类失败");
        }
        return this.create(dataCategoryEntity, true);
    }

    @Override
    public ResponseContent updateValid(Integer id, Integer status) {
        if (Objects.isNull(id)) {
            throw new ParamException("无效参数");
        }
        DataCategoryEntity oldDataCategoryEntity = new DataCategoryEntity();
        oldDataCategoryEntity.setIsValid(status);
        oldDataCategoryEntity.setUpdateAt(new Date());
        int update = dataCategoryMapper.update(oldDataCategoryEntity,
                new UpdateWrapper<DataCategoryEntity>().eq("id", id));
        if (update < 1) {
            throw new DataBaseException("更新数据源分类状态失败");
        }
        return ResponseContent.success("更新数据源分类状态成功");
    }

    @Override
    public ResponseContent detail(Integer id) {
        return ResponseContent.success(dataCategoryMapper.queryDetailById(id));
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
        lastDataCategoryList.forEach(lastDataCategory -> lastDataCategoryMap.put(lastDataCategory.getNo(), lastDataCategory));

        List<String> ignoreList = new ArrayList<>();
        ignoreList.add("version");
        ignoreList.add("isValid");
        for (SceneStruct.DataCategory dataCategory : dataCategoryList) {
            List<DetailCompareResult> detailCompareResultList;
            if (lastDataCategoryMap.containsKey(dataCategory.getNo())) {
                SceneStruct.DataCategory lastDataCategory = lastDataCategoryMap.get(dataCategory.getNo());
                detailCompareResultList = CompareUtil.compare(lastDataCategory, dataCategory, ignoreList);
                lastDataCategoryList.remove(lastDataCategory);
            } else {
                detailCompareResultList = CompareUtil.compare(null, dataCategory, null);
            }
            if (!detailCompareResultList.isEmpty()) {
                versionDiffDetailList.add(new VersionDiffDetail(dataCategory.getNo(), dataCategory.getName(), detailCompareResultList));
            }
        }

        for (SceneStruct.DataCategory lastDataCategory : lastDataCategoryList) {
            List<DetailCompareResult> detailCompareResultList = CompareUtil.compare(lastDataCategory, null, null);
            versionDiffDetailList.add(new VersionDiffDetail(lastDataCategory.getNo(), lastDataCategory.getName(), detailCompareResultList));
        }
        return versionDiffDetailList;
    }

    @Override
    public ResponseContent dropdownList(DataCategoryQueryParam param) {
        List<String> lineList = (List<String>) RequestHolder.get().get(Constant.LINE_PERMISSION_SET);
        return ResponseContent.success(dataCategoryMapper.selectDropdownList(param.getLineNo(), lineList));
    }

    @Override
    public ResponseContent trial(DataCategoryEntity dataCategoryEntity) {
        LineEntity lineEntity = lineMapper.selectByLineNo(dataCategoryEntity.getLineNo());
        if (StringUtils.isBlank(lineEntity.getUrl())) {
            return ResponseContent.error("业务线配置异常！");
        }
        String url = lineEntity.getUrl() + "/deploy/trial";
        String resp = httpUtil.post(url, gson.toJson(dataCategoryEntity));
        return gson.fromJson(resp, ResponseContent.class);
    }

}
