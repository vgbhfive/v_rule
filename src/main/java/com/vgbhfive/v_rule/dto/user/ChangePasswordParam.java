package com.vgbhfive.v_rule.dto.user;

import lombok.Data;

/**
 * @Author vgbhfive
 * @Date 2025/12/3 10:49
 */
@Data
public class ChangePasswordParam {

    private String email;

    private String oldPassword;

    private String newPassword;

    private String newDupPassword;

}
