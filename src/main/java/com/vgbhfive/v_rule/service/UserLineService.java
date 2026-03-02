package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.entity.UserLineEntity;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2026/3/2 17:11
 */
public interface UserLineService {

    ResponseContent userLineDetails(Integer userId);

    ResponseContent updateUserLineDetail(List<UserLineEntity> userLineEntityList);

}
