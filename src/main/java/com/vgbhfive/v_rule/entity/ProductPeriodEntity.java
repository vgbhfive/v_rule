package com.vgbhfive.v_rule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vgbhfive.v_rule.common.enums.PeriodType;
import com.vgbhfive.v_rule.common.enums.ValueType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author vgbhfive
 * @Date 2025/11/28 11:06
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName("product_period")
public class ProductPeriodEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 产品编码
     */
    private String productNo;

    /**
     * 值类型
     * @see ValueType
     */
    private String valueType;

    /**
     * 值
     */
    private String value;

    /**
     * 账期类型
     * @see PeriodType
     */
    private String periodType;

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
