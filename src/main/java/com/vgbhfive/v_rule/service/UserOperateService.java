package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.entity.UserOperateEntity;
import com.vgbhfive.v_rule.entity.pub.OperatePermission;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2026/3/3 16:08
 */
public interface UserOperateService {

    ResponseContent<List<OperatePermission>> operatePermissionList();

    ResponseContent<List<Integer>> userOperateDetails(Integer userId);

    ResponseContent updateUserOperateDetail(List<UserOperateEntity> userOperateEntityList);

}
