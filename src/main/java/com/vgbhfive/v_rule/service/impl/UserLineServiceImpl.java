package com.vgbhfive.v_rule.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.entity.UserLineEntity;
import com.vgbhfive.v_rule.mapper.UserLineMapper;
import com.vgbhfive.v_rule.service.UserLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author vgbhfive
 * @Date 2026/3/2 17:12
 */
@Service
public class UserLineServiceImpl implements UserLineService {

    @Autowired
    private UserLineMapper userLineMapper;

    @Override
    public ResponseContent userLineDetails(Integer userId) {
        return ResponseContent.success(userLineMapper.userLineDetails(userId));
    }

    @Override
    public ResponseContent updateUserLineDetail(List<UserLineEntity> userLineEntityList) {
        if (userLineEntityList.isEmpty()) {
            return ResponseContent.error("数据异常，请重试");
        }
        Date now = new Date();
        Integer userId = userLineEntityList.get(0).getUserId();
        List<String> lineNos = userLineMapper.userLineDetails(userId);
        if (!lineNos.isEmpty()) {
            // 修改，删除旧数据
            UserLineEntity oldUserLineEntity = new UserLineEntity();
            oldUserLineEntity.setIsDelete(1);
            oldUserLineEntity.setUpdateAt(now);
            int update = userLineMapper.update(oldUserLineEntity, new UpdateWrapper<UserLineEntity>().
                    eq("user_id", userId).eq("is_delete", 0));
            if (update != lineNos.size()) {
                return ResponseContent.error("用户业务线权限操作异常");
            }
        }
        userLineEntityList.forEach(entity -> {
            entity.setIsDelete(0);
            entity.setCreateAt(now);
            entity.setUpdateAt(now);
        });
        int insert = userLineMapper.batchInsertEntity(userLineEntityList);
        if (insert != userLineEntityList.size()) {
            return ResponseContent.error("用户业务线权限操作异常");
        }
        return ResponseContent.error("用户业务线权限操作成功");
    }

}
