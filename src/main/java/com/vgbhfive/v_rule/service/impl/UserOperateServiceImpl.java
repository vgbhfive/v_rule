package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.entity.pub.OperatePermission;
import com.vgbhfive.v_rule.mapper.OperatePermissionMapper;
import com.vgbhfive.v_rule.mapper.UserOperateMapper;
import com.vgbhfive.v_rule.service.UserOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2026/3/3 16:08
 */
@Service
public class UserOperateServiceImpl implements UserOperateService {

    @Autowired
    private OperatePermissionMapper operatePermissionMapper;
    @Autowired
    private UserOperateMapper userOperateMapper;

    @Override
    public ResponseContent<List<OperatePermission>> operatePermissionList() {
        return ResponseContent.success(operatePermissionMapper.selectList(new QueryWrapper<>()));
    }

}
