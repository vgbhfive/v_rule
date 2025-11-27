package com.vgbhfive.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.common.constants.Constant;
import com.vgbhfive.common.exception.DataBaseException;
import com.vgbhfive.common.utils.NoGenerateUtil;
import com.vgbhfive.dto.PageResponse;
import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.datacategory.DataCategoryListDto;
import com.vgbhfive.dto.datacategory.DataCategoryQueryParam;
import com.vgbhfive.entity.DataCategoryEntity;
import com.vgbhfive.mapper.DataCategoryMapper;
import com.vgbhfive.service.DataCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @projectName: v-rule
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
        if (isUpdate) {
            dataCategoryEntity.setVersion(dataCategoryEntity.getVersion() + 1);
        } else {
            dataCategoryEntity.setDataCategoryNo(noGenerateUtil.generateNo(Constant.NO_SJYFL));
        }
        dataCategoryEntity.setId(null);
        Date now = new Date();
        dataCategoryEntity.setCreateAt(now);
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

}
