package com.vgbhfive.v_rule.dto.user;

import lombok.Data;

import java.util.Date;

/**
 * @author: vgbhfive
 * @date: 2026/1/29 0:13
 */
@Data
public class UserListDto {

    private Integer id;

    private String name;

    private String mobile;

    private String email;

    private Integer status;

    private Date createAt;

    private Date updateAt;

}
