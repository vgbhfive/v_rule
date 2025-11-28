package com.vgbhfive.service;

import com.vgbhfive.dto.ResponseContent;
import com.vgbhfive.dto.divide.DivideQueryParam;
import com.vgbhfive.entity.DivideEntity;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 15:51
 */
public interface DivideService {

    ResponseContent queryList(DivideQueryParam param);

    ResponseContent create(DivideEntity divideEntity, boolean isUpdate);

    ResponseContent detail(Integer id);

    ResponseContent update(DivideEntity divideEntity);

}
