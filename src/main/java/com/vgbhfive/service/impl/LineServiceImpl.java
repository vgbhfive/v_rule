package com.vgbhfive.service.impl;

import com.vgbhfive.dto.PageResponse;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.line.LineListDto;
import com.vgbhfive.dto.line.LineQueryParam;
import com.vgbhfive.mapper.LineMapper;
import com.vgbhfive.service.LineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/24 23:26
 */
@Service
public class LineServiceImpl implements LineService {

    private static final Logger logger = LoggerFactory.getLogger(LineServiceImpl.class);

    @Autowired
    private LineMapper lineMapper;

    @Override
    public ResponseContent queryList(LineQueryParam param) {
        int start = (param.getCurrPage() - 1) * param.getLimit();
        int limit = param.getLimit();

        List<LineListDto> lineListDtoList = lineMapper.queryList(param, start, limit);
        int totalCount = lineMapper.queryTotalCount(param);

        int totalPage = (totalCount - 1) / limit + 1;
        PageResponse<LineListDto> result = new PageResponse<>(param.getCurrPage(), limit, totalCount, totalPage, lineListDtoList);
        return ResponseContent.success(result);
    }

}
