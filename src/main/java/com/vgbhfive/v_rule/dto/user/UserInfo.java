package com.vgbhfive.v_rule.dto.user;

import lombok.Data;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/12/5 0:21
 */
@Data
public class UserInfo {

    private Integer id;

    private String email;

    private String name;

    private String mobile;

    private boolean isAdmin;

}
