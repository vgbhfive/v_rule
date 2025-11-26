package com.vgbhfive.service.impl;

import com.vgbhfive.dto.PageResponse;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.datasource.DataSourceListDto;
import com.vgbhfive.dto.datasource.DataSourceQueryParam;
import com.vgbhfive.dto.line.LineListDto;
import com.vgbhfive.mapper.DataSourceMapper;
import com.vgbhfive.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/11/26 15:40
 */
@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DataSourceMapper dataSourceMapper;

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

}
