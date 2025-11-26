package com.vgbhfive.service;

import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.datacategory.DataCategoryQueryParam;
import com.vgbhfive.entity.DataCategoryEntity;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/26 21:32
 */
public interface DataCategoryService {

    ResponseContent queryList(DataCategoryQueryParam param);

    ResponseContent create(DataCategoryEntity dataCategoryEntity, boolean isUpdate);

    ResponseContent update(DataCategoryEntity dataCategoryEntity);

}
