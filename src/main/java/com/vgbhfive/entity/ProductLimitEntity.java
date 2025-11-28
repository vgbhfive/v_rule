package com.vgbhfive.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 11:24
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName("product_limit")
public class ProductLimitEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String productNo;

    /**
     * 值类型
     * @see com.vgbhfive.common.enums.ValueType
     */
    private String valueType;

    /**
     * 值
     */
    private String value;

    /**
     * 版本
     */
    private Integer version;

    private Integer isDelete;

    private Date createAt;

    private Date updateAt;

    @TableField(exist = false)
    private String lineNo;

    @TableField(exist = false)
    private String productName;

    @TableField(exist = false)
    private String type;

    @TableField(exist = false)
    private String remark;

    @TableField(exist = false)
    private Integer isValid;

}
