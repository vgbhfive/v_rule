package com.vgbhfive.v_rule.dto.user;

import lombok.Data;

/**
 * @Author vgbhfive
 * @Date 2025/12/3 14:54
 */
@Data
public class LoginReq {

    private String email;

    private String password;

    // 是否验证
    private Boolean captcha;

}
