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
 * @Date 2026/3/2 17:09
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName("user_operate")
public class UserOperateEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String uniqueSign;

    private Integer type;

    private Integer isDelete;

    private Date createAt;

    private Date updateAt;

}
