package com.vgbhfive.v_rule.dto.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/12/5 0:21
 */
@Data
public class UserInfo implements Serializable {

    private Integer id;

    private String email;

    private String name;

    private String mobile;

    private boolean isAdmin;

    /**
     * 当前用户可访问的业务线权限
     */
    private List<String> lineNoSet;

}
