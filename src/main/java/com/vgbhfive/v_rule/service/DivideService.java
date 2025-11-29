package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.divide.DivideQueryParam;
import com.vgbhfive.v_rule.entity.DivideEntity;

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
