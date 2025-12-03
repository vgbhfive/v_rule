package com.vgbhfive.v_rule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author vgbhfive
 * @Date 2025/12/3 10:17
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName("user")
public class UserEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 盐值
     */
    private Integer salt;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户状态
     * @see com.vgbhfive.v_rule.common.enums.UserStatus
     */
    private Integer status;

    private Date createAt;

    private Date updateAt;

}
