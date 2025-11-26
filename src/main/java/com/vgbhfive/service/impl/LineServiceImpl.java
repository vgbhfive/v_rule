package com.vgbhfive.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.common.exception.DataBaseException;
import com.vgbhfive.dto.PageResponse;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.line.LineListDto;
import com.vgbhfive.dto.line.LineQueryParam;
import com.vgbhfive.entity.LineEntity;
import com.vgbhfive.mapper.LineMapper;
import com.vgbhfive.service.LineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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

    @Override
    public ResponseContent create(LineEntity lineEntity) {
        lineEntity.setId(null);
        Date now = new Date();
        lineEntity.setCreateAt(now);
        lineEntity.setUpdateAt(now);
        Integer insert = lineMapper.insert(lineEntity);
        if (insert < 1) {
            throw new DataBaseException("创建业务线失败");
        }
        return ResponseContent.success();
    }

    @Override
    public ResponseContent update(LineEntity lineEntity) {
        LineEntity oldLineEntity = new LineEntity();
        oldLineEntity.setIsDelete(1);
        oldLineEntity.setUpdateAt(new Date());
        Integer update = lineMapper.update(oldLineEntity,
                new UpdateWrapper<LineEntity>().eq("id", lineEntity.getId()).eq("is_delete", 0));
        if (update < 1) {
            throw new DataBaseException("更新业务线失败");
        }
        return this.create(lineEntity);
    }

}
