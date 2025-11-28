package com.vgbhfive.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 14:34
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName("divide")
public class DivideEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

}
