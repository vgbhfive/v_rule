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
 * @Date 2026/2/25 14:31
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName("data_category_detail")
public class DataCategoryDetailEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 数据源分类编码
     */
    private String dataCategoryNo;

    /**
     * 字段名
     */
    private String key;

    /**
     * 数据源类型
     * @see com.vgbhfive.v_rule.common.enums.ValueType
     */
    private String valueType;

    /**
     * 值
     */
    private String value;

    /**
     * 试算值
     */
    private String trialValue;

    private Integer version;

    private Integer isDelete;

    private Date createAt;

    private Date updateAt;

}
