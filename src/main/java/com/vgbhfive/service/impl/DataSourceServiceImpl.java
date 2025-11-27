package com.vgbhfive.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.common.constants.Constant;
import com.vgbhfive.common.exception.DataBaseException;
import com.vgbhfive.common.utils.NoGenerateUtil;
import com.vgbhfive.dto.PageResponse;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.datasource.DataSourceListDto;
import com.vgbhfive.dto.datasource.DataSourceQueryParam;
import com.vgbhfive.entity.DataSourceEntity;
import com.vgbhfive.mapper.DataSourceMapper;
import com.vgbhfive.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
        if (isUpdate) {
            dataSourceEntity.setVersion(dataSourceEntity.getVersion() + 1);
        } else {
            dataSourceEntity.setDataSourceNo(noGenerateUtil.generateNo(Constant.NO_SJY));
        }
        dataSourceEntity.setId(null);
        Date now = new Date();
        dataSourceEntity.setCreateAt(now);
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

}
