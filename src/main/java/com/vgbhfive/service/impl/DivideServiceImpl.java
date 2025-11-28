package com.vgbhfive.service.impl;

import com.vgbhfive.common.utils.NoGenerateUtil;
import com.vgbhfive.dto.PageResponse;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.datasource.DataSourceListDto;
import com.vgbhfive.dto.divide.DivideListDto;
import com.vgbhfive.dto.divide.DivideQueryParam;
import com.vgbhfive.entity.DivideEntity;
import com.vgbhfive.mapper.DivideMapper;
import com.vgbhfive.service.DivideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 15:51
 */
@Service
public class DivideServiceImpl implements DivideService {

    @Autowired
    private DivideMapper divideMapper;
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
    public ResponseContent create(DivideEntity divideEntity, boolean isUpdate) {
        return null;
    }

    @Override
    public ResponseContent update(DivideEntity divideEntity) {
        return null;
    }


}
