package com.vgbhfive.v_rule.dto.user;

import com.vgbhfive.v_rule.dto.PageRequest;
import lombok.Data;

/**
 * @author: vgbhfive
 * @date: 2026/1/29 0:10
 */
@Data
public class UserQueryParam extends PageRequest {

    private String name;

    private String mobile;

    private String email;

    private Integer status;

}
