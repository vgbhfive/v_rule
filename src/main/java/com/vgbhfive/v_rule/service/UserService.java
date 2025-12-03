package com.vgbhfive.v_rule.service;

import com.vgbhfive.v_rule.dto.ResponseContent;
import com.vgbhfive.v_rule.dto.user.ChangePasswordParam;
import com.vgbhfive.v_rule.dto.user.LoginReq;
import com.vgbhfive.v_rule.entity.UserEntity;

/**
 * @Author vgbhfive
 * @Date 2025/12/3 10:33
 */
public interface UserService {

    ResponseContent login(LoginReq loginReq);

    ResponseContent register(UserEntity userEntity);

    ResponseContent resetPassword(String email);

    ResponseContent changePassword(ChangePasswordParam param);

}
