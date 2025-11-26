package com.vgbhfive.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author vgbhfive
 * @Date 2025/11/26 12:17
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
@TableName("")
public class DataSourceEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;



}
