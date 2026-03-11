package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.entity.UserOperateEntity;
import com.vgbhfive.v_rule.entity.pub.OperatePermission;
import com.vgbhfive.v_rule.mapper.OperatePermissionMapper;
import com.vgbhfive.v_rule.mapper.UserOperateMapper;
import com.vgbhfive.v_rule.service.UserOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        List<OperatePermission> operatePermissionList = operatePermissionMapper.queryList();
        Map<Integer, OperatePermission> operateMap = operatePermissionList.stream()
                .collect(Collectors.toMap(OperatePermission::getId, node -> node));
        List<OperatePermission> level = new ArrayList<>();
        operatePermissionList.forEach(operate -> {
            if (operate.getParentId().equals(0)) {
                level.add(operate);
            } else {
                OperatePermission tmp = operateMap.get(operate.getParentId());
                if (Objects.nonNull(tmp)) {
                    tmp.getChildren().add(operate);
                }
            }
        });
        return ResponseContent.success(level);
    }

    @Override
    public ResponseContent<List<Integer>> userOperateDetails(Integer userId) {
        return ResponseContent.success(userOperateMapper.userOperateDetails(userId));
    }

    @Override
    public ResponseContent updateUserOperateDetail(List<UserOperateEntity> userOperateEntityList) {
        if (userOperateEntityList.isEmpty()) {
            return ResponseContent.error("数据异常，请重试");
        }
        Date now = new Date();
        Integer userId = userOperateEntityList.get(0).getUserId();
        List<Integer> operateIdList = userOperateMapper.userOperateDetails(userId);
        if (!operateIdList.isEmpty()) {
            // 修改，删除旧数据
            UserOperateEntity oldUserOperateEntity = new UserOperateEntity();
            oldUserOperateEntity.setIsDelete(1);
            oldUserOperateEntity.setUpdateAt(now);
            int update = userOperateMapper.update(oldUserOperateEntity, new UpdateWrapper<UserOperateEntity>().
                    eq("user_id", userId).eq("is_delete", 0));
            if (update != operateIdList.size()) {
                return ResponseContent.error("用户权限操作异常");
            }
        }
        userOperateEntityList.forEach(entity -> {
            entity.setIsDelete(0);
            entity.setCreateAt(now);
            entity.setUpdateAt(now);
        });
        int insert = userOperateMapper.batchInsertEntity(userOperateEntityList);
        if (insert != userOperateEntityList.size()) {
            return ResponseContent.error("用户权限操作异常");
        }
        return ResponseContent.success("用户权限操作成功");
    }

}
