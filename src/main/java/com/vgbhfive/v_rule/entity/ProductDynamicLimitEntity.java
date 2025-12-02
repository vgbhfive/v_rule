package com.vgbhfive.v_rule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vgbhfive.v_rule.common.enums.ValueType;
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
@TableName("product_dynamic_limit")
public class ProductDynamicLimitEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String productNo;

    /**
     * 最小值类型
     * @see ValueType
     */
    private String minValueType;

    /**
     * 最小值
     */
    private String minValue;

    /**
     * 最大值类型
     * @see ValueType
     */
    private String maxValueType;

    /**
     * 最大值
     */
    private String maxValue;

    /**
     * 步进值类型
     * @see ValueType
     */
    private String stepType;

    /**
     * 步进值
     */
    private String stepValue;

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
