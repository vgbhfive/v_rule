package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.exception.DataBaseException;
import com.vgbhfive.v_rule.common.exception.ParamException;
import com.vgbhfive.v_rule.common.utils.CompareUtil;
import com.vgbhfive.v_rule.common.utils.NoGenerateUtil;
import com.vgbhfive.v_rule.dto.PageResponse;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.datasource.DataSourceListDto;
import com.vgbhfive.v_rule.dto.datasource.DataSourceQueryParam;
import com.vgbhfive.v_rule.dto.deploy.DetailCompareResult;
import com.vgbhfive.v_rule.dto.deploy.SceneStruct;
import com.vgbhfive.v_rule.dto.deploy.VersionDiffDetail;
import com.vgbhfive.v_rule.entity.DataSourceEntity;
import com.vgbhfive.v_rule.mapper.DataSourceMapper;
import com.vgbhfive.v_rule.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author vgbhfive
 * @Date 2025/11/26 15:40
 */
@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DataSourceMapper dataSourceMapper;
    @Resource
    private NoGenerateUtil noGenerateUtil;

    @Override
    public ResponseContent queryList(DataSourceQueryParam param) {
        int start = (param.getCurrPage() - 1) * param.getLimit();
        int limit = param.getLimit();

        List<DataSourceListDto> lineListDtoList = dataSourceMapper.queryList(param, start, limit);
        int totalCount = dataSourceMapper.queryTotalCount(param);

        int totalPage = (totalCount - 1) / limit + 1;
        PageResponse<DataSourceListDto> result = new PageResponse<>(param.getCurrPage(), limit, totalCount, totalPage, lineListDtoList);
        return ResponseContent.success(result);
    }

    @Override
    public ResponseContent create(DataSourceEntity dataSourceEntity, boolean isUpdate) {
        Date now = new Date();
        if (isUpdate) {
            dataSourceEntity.setVersion(dataSourceEntity.getVersion() + 1);
        } else {
            dataSourceEntity.setDataSourceNo(noGenerateUtil.generateNo(Constant.NO_SJY));
            dataSourceEntity.setVersion(1);
            dataSourceEntity.setCreateAt(now);
        }
        dataSourceEntity.setId(null);
        dataSourceEntity.setIsValid(1);
        dataSourceEntity.setIsDelete(0);
        dataSourceEntity.setUpdateAt(now);
        Integer insert = dataSourceMapper.insert(dataSourceEntity);
        if (insert < 1) {
            throw new DataBaseException("创建数据源失败");
        }
        return ResponseContent.success();
    }

    @Override
    public ResponseContent update(DataSourceEntity dataSourceEntity) {
        DataSourceEntity oldDataSourceEntity = new DataSourceEntity();
        oldDataSourceEntity.setIsDelete(1);
        oldDataSourceEntity.setUpdateAt(new Date());
        Integer update = dataSourceMapper.update(oldDataSourceEntity,
                new UpdateWrapper<DataSourceEntity>().eq("id", dataSourceEntity.getId()).eq("is_delete", 0));
        if (update < 1) {
            throw new DataBaseException("更新数据源失败");
        }
        return this.create(dataSourceEntity, true);
    }

    @Override
    public ResponseContent updateValid(Integer id, Integer status) {
        if (Objects.isNull(id)) {
            throw new ParamException("无效参数");
        }
        DataSourceEntity oldDataSourceEntity = new DataSourceEntity();
        oldDataSourceEntity.setIsValid(status);
        oldDataSourceEntity.setUpdateAt(new Date());
        int update = dataSourceMapper.update(oldDataSourceEntity,
                new UpdateWrapper<DataSourceEntity>().eq("id", id));
        if (update < 1) {
            throw new DataBaseException("更新数据源状态失败");
        }
        return ResponseContent.success("更新数据源状态成功");
    }

    @Override
    public List<SceneStruct.DataSource> queryDataSourceByDataSourceNos(Set<String> dataSourceNoSet) {
        if (dataSourceNoSet.isEmpty()) {
            return new ArrayList<>();
        }
        return dataSourceMapper.queryDataSourceByDataSourceNos(dataSourceNoSet);
    }

    @Override
    public List<VersionDiffDetail> queryDeployDiff(List<SceneStruct.DataSource> dataSourceList, List<SceneStruct.DataSource> lastDataSourceList) throws Exception {
        List<VersionDiffDetail> versionDiffDetailList = new ArrayList<>();
        Map<String, SceneStruct.DataSource> lastDataSourceMap = new HashMap<>();
        lastDataSourceList.forEach(lastDataSource -> lastDataSourceMap.put(lastDataSource.getNo(), lastDataSource));

        List<String> ignoreList = new ArrayList<>();
        ignoreList.add("version");
        ignoreList.add("isValid");
        for (SceneStruct.DataSource dataSource : dataSourceList) {
            List<DetailCompareResult> detailCompareResultList;
            if (lastDataSourceMap.containsKey(dataSource.getNo())) {
                SceneStruct.DataSource lastDataSource = lastDataSourceMap.get(dataSource.getNo());
                detailCompareResultList = CompareUtil.compare(lastDataSource, dataSource, ignoreList);
                lastDataSourceList.remove(lastDataSource);
            } else {
                detailCompareResultList = CompareUtil.compare(null, dataSource, null);
            }
            if (!detailCompareResultList.isEmpty()) {
                versionDiffDetailList.add(new VersionDiffDetail(dataSource.getNo(), dataSource.getName(), detailCompareResultList));
            }
        }

        for (SceneStruct.DataSource lastDataSource : lastDataSourceList) {
            List<DetailCompareResult> detailCompareResultList = CompareUtil.compare(lastDataSource, null, null);
            versionDiffDetailList.add(new VersionDiffDetail(lastDataSource.getNo(), lastDataSource.getName(), detailCompareResultList));
        }
        return versionDiffDetailList;
    }

    @Override
    public ResponseContent dropdownList() {
        return ResponseContent.success(dataSourceMapper.selectDropdownList());
    }

}
