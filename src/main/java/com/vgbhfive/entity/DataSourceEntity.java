package com.vgbhfive.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author vgbhfive
 * @Date 2025/11/26 12:17
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
@TableName("data_source")
public class DataSourceEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 业务线编码
     */
    private String lineNo;

    /**
     * 数据源名称
     */
    private String dataSourceName;

    /**
     * 数据源编码
     */
    private String dataSourceNo;

    /**
     * 数据源分类编码
     */
    private String dataCategoryNo;

    /**
     * 一级来源
     */
    private String sourceFrom;

    /**
     * 二级来源
     */
    private String sourceType;

    /**
     * 字段
     */
    private String field;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段格式
     */
    private String format;

    /**
     * 版本
     */
    private Integer version;

    private Integer isValid;

    private Integer isDelete;

    private Date createAt;

    private Date updateAt;

}
