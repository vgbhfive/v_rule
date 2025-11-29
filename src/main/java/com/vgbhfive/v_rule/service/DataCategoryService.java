package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.datacategory.DataCategoryQueryParam;
import com.vgbhfive.v_rule.entity.DataCategoryEntity;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/26 21:32
 */
public interface DataCategoryService {

    ResponseContent queryList(DataCategoryQueryParam param);

    ResponseContent create(DataCategoryEntity dataCategoryEntity, boolean isUpdate);

    ResponseContent update(DataCategoryEntity dataCategoryEntity);

}
