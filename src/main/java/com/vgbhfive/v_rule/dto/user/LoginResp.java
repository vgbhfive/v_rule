package com.vgbhfive.v_rule.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author vgbhfive
 * @Date 2025/12/5 17:05
 */
@Data
@AllArgsConstructor
public class LoginResp {

    private String email;

    private String name;

    private String mobile;

    private String token;

}
