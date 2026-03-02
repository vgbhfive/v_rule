package com.vgbhfive.v_rule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * @Author vgbhfive
 * @Date 2026/3/2 17:09
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName("user_line")
public class UserLineEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String lineNo;

    private Integer isDelete;

    private Date createAt;

    private Date updateAt;

}
